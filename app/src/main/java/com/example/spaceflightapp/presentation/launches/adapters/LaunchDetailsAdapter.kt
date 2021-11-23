package com.example.spaceflightapp.presentation.launches.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceflightapp.R
import com.example.spaceflightapp.presentation.launches.LaunchUi

class LaunchDetailsAdapter(private val items: List<LaunchUi<*>>) :
    RecyclerView.Adapter<LaunchDetailViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        ViewType.MISSION_NAME.ordinal -> MissionNameViewHolder(parent.makeView(R.layout.title))
        ViewType.ROCKET.ordinal -> RocketViewHolder(parent.makeView(R.layout.subtitle))
        ViewType.FLIGHT_NUMBER.ordinal -> FlightNumberViewHolder(parent.makeView(R.layout.subtitle))
        ViewType.LAUNCH_PLACE.ordinal -> LaunchPlaceViewHolder(parent.makeView(R.layout.subtitle))
        ViewType.LAUNCH_YEAR.ordinal -> LaunchYearViewHolder(parent.makeView(R.layout.subtitle))
        ViewType.LAUNCH_DATE.ordinal -> LaunchDateViewHolder(parent.makeView(R.layout.subtitle))
        ViewType.LAUNCH_SUCCESS.ordinal -> LaunchSuccessViewHolder(parent.makeView(R.layout.checkbox))
        ViewType.DETAILS.ordinal -> DetailsViewHolder(parent.makeView(R.layout.subtitle))
        ViewType.SHIPS.ordinal -> ShipsViewHolder(parent.makeView(R.layout.subtitle))
        ViewType.LINK.ordinal -> LinkViewHolder(parent.makeView(R.layout.link))
        ViewType.IMAGE.ordinal -> ImageViewHolder(parent.makeView(R.layout.image))
        ViewType.PDF.ordinal -> PDFViewHolder(parent.makeView(R.layout.link))
        else -> throw UnsupportedOperationException("unknown type of item")
    }

    override fun getItemViewType(position: Int) = when (items[position]) {
        is LaunchUi.MissionName -> ViewType.MISSION_NAME.ordinal
        is LaunchUi.Rocket -> ViewType.ROCKET.ordinal
        is LaunchUi.FlightNumber -> ViewType.FLIGHT_NUMBER.ordinal
        is LaunchUi.LaunchPlace -> ViewType.LAUNCH_PLACE.ordinal
        is LaunchUi.LaunchYear -> ViewType.LAUNCH_YEAR.ordinal
        is LaunchUi.LaunchDate -> ViewType.LAUNCH_DATE.ordinal
        is LaunchUi.LaunchSuccess -> ViewType.LAUNCH_SUCCESS.ordinal
        is LaunchUi.Details -> ViewType.DETAILS.ordinal
        is LaunchUi.Ships -> ViewType.SHIPS.ordinal
        is LaunchUi.LinkTitle -> ViewType.LINK.ordinal
        is LaunchUi.Image -> ViewType.IMAGE.ordinal
        is LaunchUi.PDF -> ViewType.PDF.ordinal
    }

    override fun onBindViewHolder(holder: LaunchDetailViewHolder<*>, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size
}

abstract class LaunchDetailViewHolder<T : LaunchUi<*>>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(model: Any)
}

private enum class ViewType {
    MISSION_NAME,
    ROCKET,
    FLIGHT_NUMBER,
    LAUNCH_PLACE,
    LAUNCH_YEAR,
    LAUNCH_DATE,
    LAUNCH_SUCCESS,
    DETAILS,
    SHIPS,
    LINK,
    IMAGE,
    PDF,
}

fun ViewGroup.makeView(@LayoutRes layoutResId: Int): View =
    LayoutInflater.from(this.context).inflate(layoutResId, this, false)