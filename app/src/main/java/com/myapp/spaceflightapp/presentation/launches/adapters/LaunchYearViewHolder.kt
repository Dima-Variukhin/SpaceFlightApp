package com.myapp.spaceflightapp.presentation.launches.adapters

import android.view.View
import android.widget.TextView
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.presentation.launches.LaunchUi


class LaunchYearViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.LaunchYear>(view) {
    private val textView: TextView = itemView.findViewById(R.id.textView)
    override fun bind(model: Any) {
        val value = (model as LaunchUi.LaunchYear).value
        textView.text = itemView.context.getString(R.string.launch_year, value)
    }
}