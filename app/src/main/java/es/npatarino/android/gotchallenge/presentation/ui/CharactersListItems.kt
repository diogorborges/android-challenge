package es.npatarino.android.gotchallenge.presentation.ui

import android.view.View
import es.npatarino.android.gotchallenge.R
import es.npatarino.android.gotchallenge.common.clickWithDebounce
import es.npatarino.android.gotchallenge.data.model.character.Character
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractSectionableItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder
import io.reactivex.subjects.Subject
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_source.descriptionText
import kotlinx.android.synthetic.main.item_source.sourceTitleText
import kotlinx.android.synthetic.main.item_source.urlText

class CharactersListItems(
    listHeader: ListHeader,
    private val character: Character,
    private val openCharacterObserver: Subject<Character>
) : AbstractSectionableItem<CharactersListItems.ViewHolder, ListHeader>(listHeader) {

    override fun getLayoutRes(): Int = R.layout.item_source

    override fun createViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<*>>
    ): ViewHolder =
        ViewHolder(view, adapter)

    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<*>>,
        holder: ViewHolder,
        position: Int,
        payloads: List<Any>
    ) = holder.bind(
        character,
        openCharacterObserver
    )

    class ViewHolder(override var containerView: View?, adapter: FlexibleAdapter<*>) :
        FlexibleViewHolder(containerView, adapter), LayoutContainer {

        fun bind(
            character: Character,
            openCharacterObserver: Subject<Character>
        ) {
            setSourceTitleText(character)
            setDescriptionText(character)
            setUrlText(character)

            setOpenArticleDetails(character, openCharacterObserver)
        }

        private fun setSourceTitleText(character: Character) = with(sourceTitleText) {
            text = character.name
        }

        private fun setDescriptionText(character: Character) = with(descriptionText) {
            text = character.imageUrl
        }

        private fun setUrlText(character: Character) = with(urlText) {
            text = character.description
        }

        private fun setOpenArticleDetails(
            character: Character,
            openCharacterObserver: Subject<Character>
        ) = containerView?.clickWithDebounce {
            openCharacterObserver.onNext(character)
        }
    }

    override fun equals(other: Any?): Boolean =
        if (other is CharactersListItems) character == other.character
        else false

    override fun hashCode(): Int {
        return character.hashCode()
    }
}