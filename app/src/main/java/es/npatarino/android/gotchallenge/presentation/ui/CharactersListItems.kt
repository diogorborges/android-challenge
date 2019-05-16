package es.npatarino.android.gotchallenge.presentation.ui

import android.view.View
import es.npatarino.android.gotchallenge.R
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_ALISSER
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_ARYA
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_BERIC
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_BROHN
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_CERSEI
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_DAENERYS
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_DORAN
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_EDDARD
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_GREGOR
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_GREY
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_HODOR
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_JAIME
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_JON
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_JORAH
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_KHAL
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_LANCEL
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_LORAS
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_LORD
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_LYSA
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_MARGAERY
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_MYRCELLA
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_OBARA
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_OBERYN
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_OLLY
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_PETYR
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_RENLY
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_ROBIN
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_SANSA
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_STANNIS
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_THEON
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_TYRION
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_WALDER
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_XARO
import es.npatarino.android.gotchallenge.common.CharactersName.Companion.CHARACTER_YARA
import es.npatarino.android.gotchallenge.common.clickWithDebounce
import es.npatarino.android.gotchallenge.common.getCharacterImage
import es.npatarino.android.gotchallenge.common.setThumbnailImage
import es.npatarino.android.gotchallenge.common.visible
import es.npatarino.android.gotchallenge.data.model.Character
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractSectionableItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder
import io.reactivex.subjects.Subject
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list.descriptionText
import kotlinx.android.synthetic.main.item_list.nameText
import kotlinx.android.synthetic.main.item_list.thumbnailImage

class CharactersListItems(
    listHeader: ListHeader,
    private val character: Character,
    private val openCharacterObserver: Subject<Character>
) : AbstractSectionableItem<CharactersListItems.ViewHolder, ListHeader>(listHeader) {

    override fun getLayoutRes(): Int = R.layout.item_list

    override fun createViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<*>>
    ): ViewHolder = ViewHolder(view, adapter)

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
            setCharacterName(character)
            setCharacterDescription(character)
            setCharacterImage(character)

            setOpenArticleDetails(character, openCharacterObserver)
        }

        private fun setCharacterName(character: Character) = with(nameText) {
            text = character.name
        }

        private fun setCharacterDescription(character: Character) = with(descriptionText) {
            descriptionText.visible()
            text = character.description
        }

        private fun setCharacterImage(character: Character) = with(thumbnailImage) {
            setThumbnailImage(this, getCharacterImage(character.name))
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

    override fun hashCode(): Int = character.hashCode()
}

