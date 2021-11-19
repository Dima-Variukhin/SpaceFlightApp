package com.example.spaceflightapp.presentation.blogs

import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import com.example.spaceflightapp.R
import com.example.spaceflightapp.core.*

class BlogAdapter(
    private val retry: Retry,
    private val clickListener: ClickListener<BlogUi>,
    private val shareListener: ClickListener<BlogUi>,
    private val favoriteListener: ClickListener<BlogUi>
) : BaseAdapter<BlogUi, BaseViewHolder<BlogUi>>() {

    override fun getItemViewType(position: Int) = when (list[position]) {
        is BlogUi.Base -> 0
        is BlogUi.Fail -> 1
        is BlogUi.Progress -> 2
        else -> 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BlogUi> =
        when (viewType) {
            0 -> BlogsViewHolder.Base(
                R.layout.text_layout.makeView(parent),
                clickListener,
                shareListener,
                favoriteListener
            )
            1 -> BaseViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
            else -> BaseViewHolder.FullScreenProgress(R.layout.progress_fullscreen.makeView(parent))
        }

    abstract class BlogsViewHolder(view: View) : BaseViewHolder<BlogUi>(view) {
        class Base(
            view: View,
            private val clickListener: ClickListener<BlogUi>,
            private val shareListener: ClickListener<BlogUi>,
            private val favoriteListener: ClickListener<BlogUi>
        ) : BlogsViewHolder(view) {
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
            override fun bind(item: BlogUi) {
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
                    favorite.setImageResource(R.drawable.outline_favorite_black_24)
                }
            }
        }
    }
}