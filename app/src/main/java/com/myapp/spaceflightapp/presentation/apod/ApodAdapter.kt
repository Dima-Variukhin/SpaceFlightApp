package com.myapp.spaceflightapp.presentation.apod

import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.core.*

class ApodAdapter(
    private val retry: Retry,
    private val shareListener: ClickListener<ApodUi>,
    private val favoriteListener: ClickListener<ApodUi>,
) : BaseAdapter<ApodUi, BaseViewHolder<ApodUi>>() {
    override fun getItemViewType(position: Int) = when (list[position]) {
        is ApodUi.Base -> 0
        is ApodUi.Fail -> 1
        is ApodUi.Progress -> 2
        else -> 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ApodUi> =
        when (viewType) {
            0 -> ApodViewHolder.Base(
                R.layout.apod_layout.makeView(parent),
                shareListener,
                favoriteListener
            )
            1 -> BaseViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
            else -> BaseViewHolder.FullScreenProgress(R.layout.progress_fullscreen.makeView(parent))
        }

    abstract class ApodViewHolder(view: View) : BaseViewHolder<ApodUi>(view) {
        class Base(
            view: View,
            private val shareListener: ClickListener<ApodUi>,
            private val favoriteLister: ClickListener<ApodUi>,
        ) : ApodViewHolder(view) {
            private val title = itemView.findViewById<CustomTextViewTitle>(R.id.title)
            private val summary = itemView.findViewById<CustomTextViewSummary>(R.id.summary)
            private val published = itemView.findViewById<CustomTextViewPublished>(R.id.publishedAt)
            private val updated = itemView.findViewById<CustomTextViewUpdated>(R.id.updatedAt)
            private val image = itemView.findViewById<CustomImageView>(R.id.image)
            private val shareButton = itemView.findViewById<ImageButton>(R.id.share)
            private val sml = itemView.findViewById<SwipeMenuLayout>(R.id.sml)
            private val favorite = itemView.findViewById<CustomFavorite>(R.id.favorite)

            override fun bind(item: ApodUi) {
                item.map(title)
                item.map(summary)
                item.map(published)
                item.map(updated)
                item.map(image)
                item.map(favorite)
                shareButton.setOnClickListener {
                    shareListener.click(item)
                    sml.smoothClose()
                }
                favorite.setOnClickListener {
                    favoriteLister.click(item)
                    favorite.setImageResource(R.drawable.outline_favorite_black_24)
                }
            }
        }
    }
}