package es.npatarino.android.gotchallenge.presentation.charactershouse

import es.npatarino.android.gotchallenge.data.model.Character
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem

interface CharactersHouseContract {

    interface View {
        fun showLoader(show: Boolean)
        fun showCharacters(charactersList: List<AbstractFlexibleItem<*>>)
        fun showError(message: String?)
        fun onCharacterClicked(character: Character)
    }

    interface Presenter {
        fun setView(
            charactersHouseFragment: CharactersHouseFragment,
            it: String
        )
    }
}