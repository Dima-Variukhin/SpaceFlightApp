package com.example.spaceflightapp.presentation.favorites

import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import com.example.spaceflightapp.R
import com.example.spaceflightapp.core.*

class FavoriteAdapter(
    private val retry: Retry,
    private val clickListener: ClickListener<FavoriteUi>,
    private val shareListener: ClickListener<FavoriteUi>,
    private val favoriteListener: ClickListener<FavoriteUi>
) : BaseAdapter<FavoriteUi, BaseViewHolder<FavoriteUi>>() {

    override fun getItemViewType(position: Int) = when (list[position]) {
        is FavoriteUi.Base -> 0
        is FavoriteUi.Fail -> 1
        is FavoriteUi.Progress -> 2
        else -> 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<FavoriteUi> =
        when (viewType) {
            0 -> FavoritesViewHolder.Base(
                R.layout.text_layout.makeView(parent),
                clickListener,
                shareListener,
                favoriteListener
            )
            1 -> BaseViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
            else -> BaseViewHolder.FullScreenProgress(R.layout.progress_fullscreen.makeView(parent))
        }

    abstract class FavoritesViewHolder(view: View) : BaseViewHolder<FavoriteUi>(view) {
        class Base(
            view: View,
            private val clickListener: ClickListener<FavoriteUi>,
            private val shareListener: ClickListener<FavoriteUi>,
            private val favoriteListener: ClickListener<FavoriteUi>
        ) : FavoritesViewHolder(view) {
            private val title = itemView.findViewById<CustomTextViewTitle>(R.id.title)
            private val summary = itemView.findViewById<CustomTextViewSummary>(R.id.summary)
            private val news = itemView.findViewById<CustomTextViewNews>(R.id.newsSite)
            private val published = itemView.findViewById<CustomTextViewPublished>(R.id.publishedAt)
            private val updated = itemView.findViewById<CustomTextViewUpdated>(R.id.updatedAt)
            private val image = itemView.findViewById<CustomImageView>(R.id.image)
            private val shareButton = itemView.findViewById<ImageButton>(R.id.share)
            private val sml = itemView.findViewById<SwipeMenuLayout>(R.id.sml)
            private val relative = itemView.findViewById<RelativeLayout>(R.id.relative)
            private val data =
                itemView.findViewById<CustomTextViewUpdatedData>(R.id.updatedDataText)
            private val favorite = itemView.findViewById<CustomFavorite>(R.id.favorite)
            override fun bind(item: FavoriteUi) {
                item.map(title)
                item.map(summary)
                item.map(news)
                item.map(published)
                item.map(updated)
                item.map(image)
                item.map(data)
                item.map(favorite)
                shareButton.setOnClickListener {
                    shareListener.click(item)
                    sml.smoothClose()
                }
                relative.setOnClickListener {
                    clickListener.click(item)
                }
                favorite.setOnClickListener {
                    favoriteListener.click(item)
                }
            }
        }
    }
}