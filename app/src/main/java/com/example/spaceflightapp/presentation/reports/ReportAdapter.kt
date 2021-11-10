package com.example.spaceflightapp.presentation.reports

import android.view.View
import android.view.ViewGroup
import com.example.spaceflightapp.R
import com.example.spaceflightapp.core.*

class ReportAdapter(
    private val retry: Retry,
    private val clickListener: ClickListener<ReportUi>
) : BaseAdapter<ReportUi, BaseViewHolder<ReportUi>>() {

    override fun getItemViewType(position: Int) = when (list[position]) {
        is ReportUi.Base -> 0
        is ReportUi.Fail -> 1
        is ReportUi.Progress -> 2
        else -> 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ReportUi> =
        when (viewType) {
            0 -> ReportsViewHolder.Base(R.layout.text_layout.makeView(parent), clickListener)
            1 -> BaseViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
            else -> BaseViewHolder.FullScreenProgress(R.layout.progress_fullscreen.makeView(parent))
        }

    abstract class ReportsViewHolder(view: View) : BaseViewHolder<ReportUi>(view) {
        class Base(view: View, private val clickListener: ClickListener<ReportUi>) :
            ReportsViewHolder(view) {
            private val title = itemView.findViewById<CustomTextViewTitle>(R.id.title)
            private val summary = itemView.findViewById<CustomTextViewSummary>(R.id.summary)
            private val news = itemView.findViewById<CustomTextViewNews>(R.id.newsSite)
            private val published = itemView.findViewById<CustomTextViewPublished>(R.id.publishedAt)
            private val updated = itemView.findViewById<CustomTextViewUpdated>(R.id.updatedAt)
            private val image = itemView.findViewById<CustomImageView>(R.id.image)
            override fun bind(item: ReportUi) {
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