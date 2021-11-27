package com.myapp.spaceflightapp.presentation.upcoming.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.presentation.upcoming.UpcomingUi

class UpcomingDetailsAdapter(private val items: List<UpcomingUi<*>>) :
    RecyclerView.Adapter<UpcomingDetailViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        ViewType.NAME.ordinal -> NameViewHolder(parent.makeView(R.layout.title))
        ViewType.LAUNCH_DATE.ordinal -> UpcomingLaunchDateViewHolder(parent.makeView(R.layout.subtitle))
        ViewType.UNIX.ordinal -> UpcomingUnixDateViewHolder(parent.makeView(R.layout.timer))
        ViewType.DATE_PRECISION.ordinal -> DatePrecisionStartViewHolder(parent.makeView(R.layout.subtitle))
        ViewType.UPCOMING.ordinal -> UpcomingViewHolder(parent.makeView(R.layout.checkbox))
        ViewType.IMAGE.ordinal -> UpcomingImageViewHolder(parent.makeView(R.layout.image))
        ViewType.REDDIT_RECOVERY.ordinal -> RedditRecoveryViewHolder(parent.makeView(R.layout.link))
        ViewType.REDDIT_CAMPAIGN.ordinal -> RedditCampaignViewHolder(parent.makeView(R.layout.link))
        ViewType.WIKI.ordinal -> WikiViewHolder(parent.makeView(R.layout.link))
        else -> throw UnsupportedOperationException("unknown type of item")
    }

    override fun getItemViewType(position: Int) = when (items[position]) {
        is UpcomingUi.Name -> ViewType.NAME.ordinal
        is UpcomingUi.LaunchDate -> ViewType.LAUNCH_DATE.ordinal
        is UpcomingUi.Unix -> ViewType.UNIX.ordinal
        is UpcomingUi.DatePrecision -> ViewType.DATE_PRECISION.ordinal
        is UpcomingUi.Upcoming -> ViewType.UPCOMING.ordinal
        is UpcomingUi.Image -> ViewType.IMAGE.ordinal
        is UpcomingUi.RedditRecovery -> ViewType.REDDIT_RECOVERY.ordinal
        is UpcomingUi.RedditCampaign -> ViewType.REDDIT_CAMPAIGN.ordinal
        is UpcomingUi.Wiki -> ViewType.WIKI.ordinal
    }

    override fun onBindViewHolder(holder: UpcomingDetailViewHolder<*>, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size
}

abstract class UpcomingDetailViewHolder<T : UpcomingUi<*>>(view: View) :
    RecyclerView.ViewHolder(view) {
    abstract fun bind(model: Any)
}

private enum class ViewType {
    NAME,
    LAUNCH_DATE,
    UNIX,
    DATE_PRECISION,
    UPCOMING,
    IMAGE,
    REDDIT_RECOVERY,
    REDDIT_CAMPAIGN,
    WIKI
}

fun ViewGroup.makeView(@LayoutRes layoutResId: Int): View =
    LayoutInflater.from(this.context).inflate(layoutResId, this, false)