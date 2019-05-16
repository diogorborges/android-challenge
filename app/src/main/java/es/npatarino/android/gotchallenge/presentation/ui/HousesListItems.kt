package es.npatarino.android.gotchallenge.presentation.ui

import android.view.View
import es.npatarino.android.gotchallenge.R
import es.npatarino.android.gotchallenge.common.clickWithDebounce
import es.npatarino.android.gotchallenge.common.getHouseImage
import es.npatarino.android.gotchallenge.common.getString
import es.npatarino.android.gotchallenge.common.setThumbnailImage
import es.npatarino.android.gotchallenge.data.model.House
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractSectionableItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder
import io.reactivex.subjects.Subject
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list.nameText
import kotlinx.android.synthetic.main.item_list.thumbnailImage

class HousesListItems(
    listHeader: ListHeader,
    private val house: House,
    private val openHouseObserver: Subject<House>
) : AbstractSectionableItem<HousesListItems.ViewHolder, ListHeader>(listHeader) {

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
        house,
        openHouseObserver
    )

    class ViewHolder(override var containerView: View?, adapter: FlexibleAdapter<*>) :
        FlexibleViewHolder(containerView, adapter), LayoutContainer {

        fun bind(
            house: House,
            openHouseObserver: Subject<House>
        ) {
            setHouseName(house)
            setHouseImage(house)

            setOpenArticleDetails(house, openHouseObserver)
        }

        private fun setHouseImage(house: House) = with(thumbnailImage) {
            setThumbnailImage(this, getHouseImage(house.houseName))
        }

        private fun setHouseName(house: House) = with(nameText) {
            text = if (house.houseName.isNotEmpty()) {
                house.houseName
            } else getString(R.string.no_name)
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