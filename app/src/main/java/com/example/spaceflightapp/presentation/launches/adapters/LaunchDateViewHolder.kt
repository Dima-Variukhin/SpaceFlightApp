package com.example.spaceflightapp.presentation.launches.adapters

import android.view.View
import android.widget.TextView
import com.example.spaceflightapp.R
import com.example.spaceflightapp.presentation.launches.LaunchUi

class LaunchDateViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.LaunchDate>(view) {
    private val textView: TextView = itemView.findViewById(R.id.textView)
    override fun bind(model: Any) {
        val value = (model as LaunchUi.LaunchDate).value
        textView.text = itemView.context.getString(R.string.launch_date, value)
    }
}