package es.npatarino.android.gotchallenge.presentation.charactershouse

import android.util.Log
import es.npatarino.android.gotchallenge.R
import es.npatarino.android.gotchallenge.common.addTo
import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.data.usecase.CharactersHouseUseCase
import es.npatarino.android.gotchallenge.presentation.ui.CharactersListItems
import es.npatarino.android.gotchallenge.presentation.ui.ListHeader
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class CharactersHousePresenter @Inject constructor(
    private val charactersHouseUseCase: CharactersHouseUseCase
) : CharactersHouseContract.Presenter {

    private val openCharacterObserver = PublishSubject.create<Character>()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var view: CharactersHouseContract.View

    companion object {
        private const val TAG = "CharHousePresenter"
    }

    override fun setView(
        charactersHouseFragment: CharactersHouseFragment,
        houseId: String
    ) {
        view = charactersHouseFragment
        setupOpenCharacterDetailsChangedEvent()
        getCharactersHouse(houseId)
    }

    private fun setupOpenCharacterDetailsChangedEvent(): Disposable =
        openCharacterObserver
            .subscribe(
                { view.onCharacterClicked(it) },
                { Log.e(TAG, "Error: $it") })

    private fun getCharactersHouse(houseId: String) {
        val disposable = charactersHouseUseCase.getCharactersHouse(houseId)
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

    fun destroy() = compositeDisposable.dispose()
}