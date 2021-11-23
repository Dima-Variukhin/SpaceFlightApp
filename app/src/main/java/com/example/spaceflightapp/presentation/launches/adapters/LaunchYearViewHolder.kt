package com.example.spaceflightapp.presentation.launches.adapters

import android.view.View
import android.widget.TextView
import com.example.spaceflightapp.R
import com.example.spaceflightapp.presentation.launches.LaunchUi


class LaunchYearViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.LaunchYear>(view) {
    private val textView: TextView = itemView.findViewById(R.id.textView)
    override fun bind(model: Any) {
        val value = (model as LaunchUi.LaunchYear).value
        textView.text = itemView.context.getString(R.string.launch_year, value)
    }
}