package com.myapp.spaceflightapp.presentation.launches.adapters

import android.view.View
import android.widget.TextView
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.presentation.launches.LaunchUi

class LaunchDateViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.LaunchDate>(view) {
    private val textView: TextView = itemView.findViewById(R.id.textView)
    override fun bind(model: Any) {
        val value = (model as LaunchUi.LaunchDate).value
        textView.text = itemView.context.getString(R.string.launch_date, value)
    }
}