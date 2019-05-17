package es.npatarino.android.gotchallenge.presentation.characters

import android.util.Log
import es.npatarino.android.gotchallenge.R
import es.npatarino.android.gotchallenge.common.addTo
import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.data.usecase.CharactersByQueryUseCase
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
    private val charactersUseCase: CharactersUseCase,
    private val charactersByQueryUseCase: CharactersByQueryUseCase
) : CharactersContract.Presenter {

    private val openCharacterObserver = PublishSubject.create<Character>()

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
        openCharacterObserver
            .subscribe(
                { view.onCharacterClicked(it) },
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

        val listHeader = ListHeader(R.string.characters_header, R.layout.list_header)

        characterList.forEach {
            listItems.add(
                CharactersListItems(
                    listHeader,
                    it,
                    openCharacterObserver
                )
            )
        }

        return listItems
    }

    private fun onError(throwable: Throwable) = view.showError(throwable.message)

    fun getCharacterByQuery(query: String) {
        val disposable = charactersByQueryUseCase.getCharacterByQuery(query)
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