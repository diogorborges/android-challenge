package es.npatarino.android.gotchallenge.presentation.houses

import android.util.Log
import es.npatarino.android.gotchallenge.R
import es.npatarino.android.gotchallenge.common.addTo
import es.npatarino.android.gotchallenge.data.model.House
import es.npatarino.android.gotchallenge.data.usecase.HousesUseCase
import es.npatarino.android.gotchallenge.presentation.ui.HousesListItems
import es.npatarino.android.gotchallenge.presentation.ui.ListHeader
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class HousesPresenter @Inject constructor(
    private val housesUseCase: HousesUseCase
) : HousesContract.Presenter {

    private val openHouseObserver = PublishSubject.create<House>()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var view: HousesContract.View

    companion object {
        private const val TAG = "HousesPresenter"
    }

    override fun setView(housesFragment: HousesFragment) {
        view = housesFragment
        setupOpenHousesDetailsChangedEvent()
        getHouses()
    }

    private fun setupOpenHousesDetailsChangedEvent(): Disposable =
        openHouseObserver
            .subscribe(
                { view.onHouseClicked(it) },
                { Log.e(TAG, "Error: $it") })

    private fun getHouses() {
        val disposable = housesUseCase.getHouses()
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

    private fun onSuccess(housesList: List<House>) =
        view.showHouses(prepareHousesList(housesList))

    private fun prepareHousesList(housesList: List<House>): List<AbstractFlexibleItem<*>> {
        val listItems = ArrayList<AbstractFlexibleItem<*>>()

        val listHeader = ListHeader(R.string.houses_header, R.layout.list_header)

        housesList.forEach {
            listItems.add(
                HousesListItems(
                    listHeader,
                    it,
                    openHouseObserver
                )
            )
        }

        return listItems
    }

    private fun onError(throwable: Throwable) = view.showError(throwable.message)

    fun destroy() = compositeDisposable.dispose()
}