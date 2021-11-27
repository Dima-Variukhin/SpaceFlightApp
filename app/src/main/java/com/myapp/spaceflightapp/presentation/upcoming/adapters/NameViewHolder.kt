package com.myapp.spaceflightapp.presentation.upcoming.adapters

import android.view.View
import android.widget.TextView
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.presentation.upcoming.UpcomingUi

class NameViewHolder(view: View) : UpcomingDetailViewHolder<UpcomingUi.Name>(view) {
    private val textView: TextView = itemView.findViewById(R.id.textView)
    override fun bind(model: Any) {
        val value = (model as UpcomingUi.Name).value
        textView.text = itemView.context.getString(R.string.mission_name, value)
    }
}