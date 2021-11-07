package com.example.spaceflightapp.presentation.articles

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.spaceflightapp.R
import com.example.spaceflightapp.core.*

class ArticleAdapter(
    private val retry: Retry,
) : BaseAdapter<ArticleUi, BaseViewHolder<ArticleUi>>() {

    override fun getItemViewType(position: Int) = when (list[position]) {
        is ArticleUi.Base -> 0
        is ArticleUi.Fail -> 1
        is ArticleUi.Progress -> 2
        else -> 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ArticleUi> =
        when (viewType) {
            0 -> ArticlesViewHolder.Base(R.layout.text_layout.makeView(parent))
            1 -> BaseViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
            else -> BaseViewHolder.FullScreenProgress(R.layout.progress_fullscreen.makeView(parent))
        }

    abstract class ArticlesViewHolder(view: View) : BaseViewHolder<ArticleUi>(view) {
        class Base(view: View) : ArticlesViewHolder(view) {
            private val title = itemView.findViewById<CustomTextViewTitle>(R.id.title)
            private val summary = itemView.findViewById<CustomTextViewSummary>(R.id.summary)
            private val news = itemView.findViewById<CustomTextViewNews>(R.id.newsSite)
            private val published = itemView.findViewById<CustomTextViewPublished>(R.id.publishedAt)
            private val updated = itemView.findViewById<CustomTextViewUpdated>(R.id.updatedAt)
            private val image = itemView.findViewById<CustomImageView>(R.id.image)
            override fun bind(item: ArticleUi) {
                item.map(title)
                item.map(summary)
                item.map(news)
                item.map(published)
                item.map(updated)
                item.map(image)

            }
        }
    }
}