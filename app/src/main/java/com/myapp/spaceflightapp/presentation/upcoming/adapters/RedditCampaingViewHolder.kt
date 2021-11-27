package com.myapp.spaceflightapp.presentation.upcoming.adapters

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.view.View
import android.widget.TextView
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.presentation.upcoming.UpcomingUi

class RedditCampaignViewHolder(view: View) :
    UpcomingDetailViewHolder<UpcomingUi.RedditCampaign>(view) {
    private val linkTextView: TextView = itemView.findViewById(R.id.linkTextView)!!
    override fun bind(model: Any) {
        val text = (model as UpcomingUi.RedditCampaign).value
        linkTextView.paintFlags =
            linkTextView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        linkTextView.text = itemView.context.getString(R.string.wiki, text)
        linkTextView.text = itemView.context.getString(R.string.reddit, text)

        itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(model.value)
            }
            itemView.context.startActivity(intent)
        }
    }
}