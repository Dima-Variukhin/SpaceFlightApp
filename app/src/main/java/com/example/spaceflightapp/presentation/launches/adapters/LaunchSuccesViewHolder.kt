package com.example.spaceflightapp.presentation.launches.adapters

import android.view.View
import android.widget.CheckBox
import com.example.spaceflightapp.R
import com.example.spaceflightapp.presentation.launches.LaunchUi

class LaunchSuccessViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.LaunchSuccess>(view) {
    private val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
    override fun bind(model: Any) {
        with(checkBox) {
            isChecked = (model as LaunchUi.LaunchSuccess).value
            setText(R.string.launch_success)
        }
    }
}