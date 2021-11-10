package com.example.spaceflightapp.presentation.blogs

import android.view.View
import android.view.ViewGroup
import com.example.spaceflightapp.R
import com.example.spaceflightapp.core.*

class BlogAdapter(
    private val retry: Retry,
    private val clickListener: ClickListener<BlogUi>
) : BaseAdapter<BlogUi, BaseViewHolder<BlogUi>>() {

    override fun getItemViewType(position: Int) = when (list[position]) {
        is BlogUi.Base -> 0
        is BlogUi.Fail -> 1
        is BlogUi.Progress -> 2
        else -> 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BlogUi> =
        when (viewType) {
            0 -> BlogsViewHolder.Base(R.layout.text_layout.makeView(parent), clickListener)
            1 -> BaseViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
            else -> BaseViewHolder.FullScreenProgress(R.layout.progress_fullscreen.makeView(parent))
        }

    abstract class BlogsViewHolder(view: View) : BaseViewHolder<BlogUi>(view) {
        class Base(view: View, private val clickListener: ClickListener<BlogUi>) :
            BlogsViewHolder(view) {
            private val title = itemView.findViewById<CustomTextViewTitle>(R.id.title)
            private val summary = itemView.findViewById<CustomTextViewSummary>(R.id.summary)
            private val news = itemView.findViewById<CustomTextViewNews>(R.id.newsSite)
            private val published = itemView.findViewById<CustomTextViewPublished>(R.id.publishedAt)
            private val updated = itemView.findViewById<CustomTextViewUpdated>(R.id.updatedAt)
            private val image = itemView.findViewById<CustomImageView>(R.id.image)
            override fun bind(item: BlogUi) {
                item.map(title)
                item.map(summary)
                item.map(news)
                item.map(published)
                item.map(updated)
                item.map(image)
                itemView.setOnClickListener {
                    clickListener.click(item)
                }
            }
        }
    }
}