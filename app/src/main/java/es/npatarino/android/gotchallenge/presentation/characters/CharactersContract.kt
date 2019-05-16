package es.npatarino.android.gotchallenge.presentation.characters

import eu.davidea.flexibleadapter.items.AbstractFlexibleItem

interface CharactersContract {

    interface View {
        fun showLoader(show: Boolean)
        fun showCharacters(sourcesList: List<AbstractFlexibleItem<*>>)
        fun showError(message: String?)
//        fun onCharacterClicked(character: Character)
    }

    interface Presenter {
        fun setView(charactersFragment: CharactersFragment)
    }
}