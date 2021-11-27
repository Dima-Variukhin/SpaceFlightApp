package com.myapp.spaceflightapp.presentation.upcoming.adapters

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.presentation.upcoming.UpcomingUi

class UpcomingImageViewHolder(view: View) : UpcomingDetailViewHolder<UpcomingUi.Image>(view) {
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)
    override fun bind(model: Any) {
        Glide.with(imageView)
            .load((model as UpcomingUi.Image).value)
            .into(imageView)
    }
}