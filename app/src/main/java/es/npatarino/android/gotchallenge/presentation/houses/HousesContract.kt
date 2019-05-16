package es.npatarino.android.gotchallenge.presentation.houses

import es.npatarino.android.gotchallenge.data.model.House
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem

interface HousesContract {

    interface View {
        fun showLoader(show: Boolean)
        fun showHouses(sourcesList: List<AbstractFlexibleItem<*>>)
        fun showError(message: String?)
        fun onHouseClicked(house: House)
    }

    interface Presenter {
        fun setView(housesFragment: HousesFragment)
    }
}