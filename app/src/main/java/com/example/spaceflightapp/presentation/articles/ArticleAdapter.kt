package com.example.spaceflightapp.presentation.articles

import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import com.example.spaceflightapp.R
import com.example.spaceflightapp.core.*

class ArticleAdapter(
    private val retry: Retry,
    private val clickListener: ClickListener<ArticleUi>,
    private val shareListener: ClickListener<ArticleUi>,
    private val favoriteListener: ClickListener<ArticleUi>,
) : BaseAdapter<ArticleUi, BaseViewHolder<ArticleUi>>() {
    override fun getItemViewType(position: Int) = when (list[position]) {
        is ArticleUi.Base -> 0
        is ArticleUi.Fail -> 1
        is ArticleUi.Progress -> 2
        else -> 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ArticleUi> =
        when (viewType) {
            0 -> ArticlesViewHolder.Base(
                R.layout.text_layout.makeView(parent),
                clickListener,
                shareListener,
                favoriteListener
            )
            1 -> BaseViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
            else -> BaseViewHolder.FullScreenProgress(R.layout.progress_fullscreen.makeView(parent))
        }

    abstract class ArticlesViewHolder(view: View) : BaseViewHolder<ArticleUi>(view) {
        class Base(
            view: View,
            private val clickListener: ClickListener<ArticleUi>,
            private val shareListener: ClickListener<ArticleUi>,
            private val favoriteLister: ClickListener<ArticleUi>,
        ) : ArticlesViewHolder(view) {
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

            override fun bind(item: ArticleUi) {
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
                    favoriteLister.click(item)
                    favorite.setImageResource(R.drawable.outline_favorite_black_24)
                }
            }
        }
    }
}