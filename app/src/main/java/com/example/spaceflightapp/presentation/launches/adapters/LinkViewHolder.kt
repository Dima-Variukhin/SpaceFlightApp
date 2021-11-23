package com.example.spaceflightapp.presentation.launches.adapters

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.view.View
import android.widget.TextView
import com.example.spaceflightapp.R
import com.example.spaceflightapp.presentation.launches.LaunchUi


class LinkViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.LinkTitle>(view) {
    private val linkTextView: TextView = itemView.findViewById(R.id.linkTextView)!!
    override fun bind(model: Any) {
        linkTextView.text = (model as LaunchUi.LinkTitle).title
        linkTextView.paintFlags =
            linkTextView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(model.value)
            }
            itemView.context.startActivity(intent)
        }
    }
}