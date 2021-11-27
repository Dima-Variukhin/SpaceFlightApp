package com.myapp.spaceflightapp.presentation.upcoming.adapters

import android.view.View
import android.widget.TextView
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.presentation.upcoming.UpcomingUi
import java.util.concurrent.TimeUnit

class UpcomingUnixDateViewHolder(view: View) :
    UpcomingDetailViewHolder<UpcomingUi.Unix>(view) {
    private val timer: TextView = itemView.findViewById(R.id.timer)
    override fun bind(model: Any) {
        val value = (model as UpcomingUi.Unix).value
        timer.text = itemView.context.getString(R.string.remain,calculateLaunchDate(value))
    }

    private fun calculateLaunchDate(value: Int): String {
        val currentTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
        val cost = value.toLong() - currentTime
        val seconds: Long = cost
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        return days.toString() + "d" + " " + hours % 24 + "h" + " " + minutes % 60 + "m"
    }
}