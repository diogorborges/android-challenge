package es.npatarino.android.gotchallenge.presentation.charactershouse

import eu.davidea.flexibleadapter.items.AbstractFlexibleItem

interface CharactersHouseContract {

    interface View {
        fun showLoader(show: Boolean)
        fun showCharacters(charactersList: List<AbstractFlexibleItem<*>>)
        fun showError(message: String?)
    }

    interface Presenter {
        fun setView(charactersHouseFragment: CharactersHouseFragment)
    }
}