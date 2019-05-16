package es.npatarino.android.gotchallenge.presentation.ui

import android.view.View
import es.npatarino.android.gotchallenge.R
import es.npatarino.android.gotchallenge.common.clickWithDebounce
import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.data.model.House
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractSectionableItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder
import io.reactivex.subjects.Subject
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_character.descriptionText
import kotlinx.android.synthetic.main.item_character.sourceTitleText
import kotlinx.android.synthetic.main.item_character.urlText

class HousesListItems(
    listHeader: ListHeader,
    private val house: House,
    private val openHouseObserver: Subject<House>
) : AbstractSectionableItem<HousesListItems.ViewHolder, ListHeader>(listHeader) {

    override fun getLayoutRes(): Int = R.layout.item_character

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
        house,
        openHouseObserver
    )

    class ViewHolder(override var containerView: View?, adapter: FlexibleAdapter<*>) :
        FlexibleViewHolder(containerView, adapter), LayoutContainer {

        fun bind(
            house: House,
            openHouseObserver: Subject<House>
        ) {
            setSourceTitleText(house)
            setDescriptionText(house)
            setUrlText(house)

            setOpenArticleDetails(house, openHouseObserver)
        }

        private fun setSourceTitleText(house: House) = with(sourceTitleText) {
            text = house.houseName
        }

        private fun setDescriptionText(house: House) = with(descriptionText) {
            text = house.houseImageUrl
        }

        private fun setUrlText(house: House) = with(urlText) {
            text = house.houseId
        }

        private fun setOpenArticleDetails(
            house: House,
            openHouseObserver: Subject<House>
        ) = containerView?.clickWithDebounce {
            openHouseObserver.onNext(house)
        }
    }

    override fun equals(other: Any?): Boolean =
        if (other is HousesListItems) house == other.house
        else false

    override fun hashCode(): Int = house.hashCode()
}