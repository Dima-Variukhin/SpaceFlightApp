package com.myapp.spaceflightapp.presentation.launches.adapters

import android.view.View
import android.widget.TextView
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.presentation.launches.LaunchUi

class DetailsViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.Details>(view) {
    override fun bind(model: Any) {
        val textView = itemView.findViewById<TextView>(R.id.textView)
        textView.text =
            itemView.context.getString(R.string.details, (model as LaunchUi.Details).value)
    }
}