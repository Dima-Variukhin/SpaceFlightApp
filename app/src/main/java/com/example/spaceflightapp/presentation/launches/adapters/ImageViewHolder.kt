package com.example.spaceflightapp.presentation.launches.adapters

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.spaceflightapp.R
import com.example.spaceflightapp.presentation.launches.LaunchUi

class ImageViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.Image>(view) {
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)
    override fun bind(model: Any) {
        Glide.with(imageView)
            .load((model as LaunchUi.Image).value)
            .into(imageView)
    }
}