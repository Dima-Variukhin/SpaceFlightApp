package com.myapp.spaceflightapp.presentation.launches.adapters

import android.view.View
import android.widget.CheckBox
import androidx.core.graphics.toColor
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.presentation.launches.LaunchUi

class LaunchSuccessViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.LaunchSuccess>(view) {
    private val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
    override fun bind(model: Any) {
        with(checkBox) {
            isChecked = (model as LaunchUi.LaunchSuccess).value
            setText(R.string.launch_success)
        }
    }
}