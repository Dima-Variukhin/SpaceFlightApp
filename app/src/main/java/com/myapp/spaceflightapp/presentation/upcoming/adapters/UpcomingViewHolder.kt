package com.myapp.spaceflightapp.presentation.upcoming.adapters

import android.view.View
import android.widget.CheckBox
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.presentation.upcoming.UpcomingUi

class UpcomingViewHolder(view: View) : UpcomingDetailViewHolder<UpcomingUi.Upcoming>(view) {
    private val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
    override fun bind(model: Any) {
        with(checkBox) {
            isChecked = (model as UpcomingUi.Upcoming).value
            setText(R.string.upcoming)
        }
    }
}