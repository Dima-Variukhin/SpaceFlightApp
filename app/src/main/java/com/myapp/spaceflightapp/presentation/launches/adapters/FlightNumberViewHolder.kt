package com.myapp.spaceflightapp.presentation.launches.adapters

import android.view.View
import android.widget.TextView
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.presentation.launches.LaunchUi

class FlightNumberViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.FlightNumber>(view) {
    private val textView: TextView = itemView.findViewById(R.id.textView)
    override fun bind(model: Any) {
        val value = (model as LaunchUi.FlightNumber).value
        textView.text = itemView.context.getString(R.string.flight_number, value)
    }
}