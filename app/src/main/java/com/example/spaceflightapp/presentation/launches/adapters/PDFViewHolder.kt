package com.example.spaceflightapp.presentation.launches.adapters

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.view.View
import android.widget.TextView
import com.example.spaceflightapp.R
import com.example.spaceflightapp.presentation.launches.LaunchUi

class PDFViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.PDF>(view) {
    private val linkTextView: TextView = itemView.findViewById(R.id.linkTextView)
    override fun bind(model: Any) {
        val modelData = (model as LaunchUi.PDF)
        with(linkTextView) {
            paintFlags = this.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            text = itemView.context.getString(R.string.pdf, modelData.title)
        }
        itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(modelData.value)
            }
            itemView.context.startActivity(intent)
        }
    }
}