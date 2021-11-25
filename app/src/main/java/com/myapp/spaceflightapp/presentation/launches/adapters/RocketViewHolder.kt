package com.myapp.spaceflightapp.presentation.launches.adapters

import android.view.View
import android.widget.TextView
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.presentation.launches.LaunchUi

class RocketViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.Rocket>(view) {
    private val textView: TextView = itemView.findViewById(R.id.textView)
    override fun bind(model: Any) {
        val modelData = model as LaunchUi.Rocket
        var firstStage = ""
        modelData.firstStageData.forEach {
            firstStage += it.first + ", " + it.second + ", "
        }
        var secondStage = modelData.secondStage.block.toString() + ", "
        modelData.secondStage.payloads.forEach {
            secondStage += it.manufacturer + ", " +
                    it.nationality + ", " +
                    it.payloadType + ", " +
                    it.payloadMassKg + ", " +
                    it.orbit + " "
        }
        val rocket = modelData.value + ", " + modelData.type + ", " + firstStage + ", " + secondStage
        textView.text = itemView.context.getString(R.string.rocket, rocket)
    }
}