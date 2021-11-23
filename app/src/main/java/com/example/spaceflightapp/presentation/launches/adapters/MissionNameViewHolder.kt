package com.example.spaceflightapp.presentation.launches.adapters

import android.view.View
import android.widget.TextView
import com.example.spaceflightapp.R
import com.example.spaceflightapp.presentation.launches.LaunchUi

class MissionNameViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.MissionName>(view) {
    private val textView: TextView = itemView.findViewById(R.id.textView)
    override fun bind(model: Any) {
        val value = (model as LaunchUi.MissionName).value
        textView.text = itemView.context.getString(R.string.mission_name, value)
    }
}