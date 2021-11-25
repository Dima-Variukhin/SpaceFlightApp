package com.myapp.spaceflightapp.presentation.launches.adapters

import android.view.View
import android.widget.TextView
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.presentation.launches.LaunchUi

class ShipsViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.Ships>(view) {
    private val textView: TextView = itemView.findViewById(R.id.textView)
    override fun bind(model: Any) {
        textView.text = itemView.context.getString(R.string.ships, (model as LaunchUi.Ships).value)
    }
}