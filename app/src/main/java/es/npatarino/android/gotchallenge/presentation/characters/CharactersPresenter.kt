package es.npatarino.android.gotchallenge.presentation.characters

import android.util.Log
import es.npatarino.android.gotchallenge.R
import es.npatarino.android.gotchallenge.common.addTo
import es.npatarino.android.gotchallenge.data.model.character.Character
import es.npatarino.android.gotchallenge.data.usecase.CharactersUseCase
import es.npatarino.android.gotchallenge.presentation.ui.CharactersListItems
import es.npatarino.android.gotchallenge.presentation.ui.ListHeader
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CharactersPresenter @Inject constructor(
    private val charactersUseCase: CharactersUseCase
) : CharactersContract.Presenter {

    private val openSourceObserver = PublishSubject.create<Character>()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var view: CharactersContract.View

    companion object {
        private const val TAG = "CharactersPresenter"
    }

    override fun setView(charactersFragment: CharactersFragment) {
        view = charactersFragment
        setupOpenCharacterDetailsChangedEvent()
        getCharacters()
    }

    private fun setupOpenCharacterDetailsChangedEvent(): Disposable =
        openSourceObserver
            .subscribe(
                { /**view.onCharacterClicked(it) */ },
                { Log.e(TAG, "Error: $it") })

    private fun getCharacters() {
        val disposable = charactersUseCase.getCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                view.showLoader(true)
            }
            .doAfterTerminate {
                view.showLoader(false)
            }
            .subscribe(this::onSuccess, this::onError)
        disposable.addTo(compositeDisposable)
    }

    private fun onSuccess(characterList: List<Character>) =
        view.showCharacters(prepareCharactersList(characterList))

    private fun prepareCharactersList(characterList: List<Character>): List<AbstractFlexibleItem<*>> {
        val listItems = ArrayList<AbstractFlexibleItem<*>>()

        val listHeader = ListHeader(R.string.sources_header, R.layout.list_header)

        characterList.forEach {
            listItems.add(
                CharactersListItems(
                    listHeader,
                    it,
                    openSourceObserver
                )
            )
        }

        return listItems
    }

    private fun onError(throwable: Throwable) = view.showError(throwable.message)

    fun searchQuery(query: String) {
        val disposable = charactersUseCase.searchQuery(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(300, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .filter { it.isNotEmpty() }
            .distinct()
            .subscribe(this::onSuccess, this::onError)
        disposable.addTo(compositeDisposable)
    }

    fun destroy() = compositeDisposable.dispose()

}