package com.example.spaceflightapp.core

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.spaceflightapp.R

//todo make all views custom except image

class CustomTextView : AppCompatTextView, AdapterNewsMapper<Unit> {
    //region constructors
    constructor(context: Context) : super(
        context
    )

    constructor(context: Context, attrs: AttributeSet) : super(
        context, attrs
    )

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    override fun map(
        id: Int,
        title: String,
        url: String,
        imageUrl: String,
        newsSite: String,
        summary: String,
        publishedAt: String,
        updatedAt: String
    ) = setText("")

    override fun map(message: String) = setText(message)
    //endregion
}

class CustomTextViewTitle : AppCompatTextView, AdapterNewsMapper<Unit> {
    //region constructors
    constructor(context: Context) : super(
        context
    )

    constructor(context: Context, attrs: AttributeSet) : super(
        context, attrs
    )

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    override fun map(
        id: Int,
        title: String,
        url: String,
        imageUrl: String,
        newsSite: String,
        summary: String,
        publishedAt: String,
        updatedAt: String
    ) = setText(title)

    override fun map(message: String) = setText(message)
    //endregion
}

class CustomTextViewSummary : AppCompatTextView, AdapterNewsMapper<Unit> {
    //region constructors
    constructor(context: Context) : super(
        context
    )

    constructor(context: Context, attrs: AttributeSet) : super(
        context, attrs
    )

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    override fun map(
        id: Int,
        title: String,
        url: String,
        imageUrl: String,
        newsSite: String,
        summary: String,
        publishedAt: String,
        updatedAt: String
    ) = setText(summary)

    override fun map(message: String) = setText(message)
    //endregion
}

class CustomTextViewNews : AppCompatTextView, AdapterNewsMapper<Unit> {
    //region constructors
    constructor(context: Context) : super(
        context
    )

    constructor(context: Context, attrs: AttributeSet) : super(
        context, attrs
    )

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    override fun map(
        id: Int,
        title: String,
        url: String,
        imageUrl: String,
        newsSite: String,
        summary: String,
        publishedAt: String,
        updatedAt: String
    ) = setText(newsSite)

    override fun map(message: String) = setText(message)
    //endregion
}

class CustomTextViewPublished : AppCompatTextView, AdapterNewsMapper<Unit> {
    //region constructors
    constructor(context: Context) : super(
        context
    )

    constructor(context: Context, attrs: AttributeSet) : super(
        context, attrs
    )

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    override fun map(
        id: Int,
        title: String,
        url: String,
        imageUrl: String,
        newsSite: String,
        summary: String,
        publishedAt: String,
        updatedAt: String
    ) = setText(publishedAt)

    override fun map(message: String) = setText(message)
    //endregion
}

class CustomTextViewUpdated : AppCompatTextView, AdapterNewsMapper<Unit> {
    //region constructors
    constructor(context: Context) : super(
        context
    )

    constructor(context: Context, attrs: AttributeSet) : super(
        context, attrs
    )

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    override fun map(
        id: Int,
        title: String,
        url: String,
        imageUrl: String,
        newsSite: String,
        summary: String,
        publishedAt: String,
        updatedAt: String
    ) = setText(updatedAt)

    override fun map(message: String) = setText(message)
    //endregion
}

class CustomImageView : AppCompatImageView, AdapterNewsMapper<Unit> {
    //region constructors
    constructor(context: Context) : super(
        context
    )

    constructor(context: Context, attrs: AttributeSet) : super(
        context, attrs
    )

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    override fun map(
        id: Int,
        title: String,
        url: String,
        imageUrl: String,
        newsSite: String,
        summary: String,
        publishedAt: String,
        updatedAt: String
    ) {
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.baseline_hourglass_bottom_24)
            .error(com.google.android.material.R.drawable.mtrl_ic_error)
            .optionalCenterCrop()
            .into(this)
    }

    override fun map(message: String) = setImageURI("".toUri())
    //endregion
}
