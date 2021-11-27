package com.myapp.spaceflightapp.presentation.upcoming.adapters

import android.os.Build
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.presentation.upcoming.UpcomingUi

class UpcomingLaunchDateViewHolder(view: View) :
    UpcomingDetailViewHolder<UpcomingUi.LaunchDate>(view) {
    private val textView: TextView = itemView.findViewById(R.id.textView)
    override fun bind(model: Any) {
        val value = (model as UpcomingUi.LaunchDate).value
        textView.text = itemView.context.getString(R.string.launch_date, value)
    }
}