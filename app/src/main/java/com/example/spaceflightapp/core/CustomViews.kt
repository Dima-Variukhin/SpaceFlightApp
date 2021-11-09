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
        idA: Int,
        titleA: String,
        urlA: String,
        imageUrlA: String,
        newsSiteA: String,
        summaryA: String,
        publishedAtA: String,
        updatedAtA: String
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
        idA: Int,
        titleA: String,
        urlA: String,
        imageUrlA: String,
        newsSiteA: String,
        summaryA: String,
        publishedAtA: String,
        updatedAtA: String
    ) = setText(titleA)

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
        idA: Int,
        titleA: String,
        urlA: String,
        imageUrlA: String,
        newsSiteA: String,
        summaryA: String,
        publishedAtA: String,
        updatedAtA: String
    ) = setText(summaryA)

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
        idA: Int,
        titleA: String,
        urlA: String,
        imageUrlA: String,
        newsSiteA: String,
        summaryA: String,
        publishedAtA: String,
        updatedAtA: String
    ) = setText(newsSiteA)

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
        idA: Int,
        titleA: String,
        urlA: String,
        imageUrlA: String,
        newsSiteA: String,
        summaryA: String,
        publishedAtA: String,
        updatedAtA: String
    ) = setText(publishedAtA)

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
        idA: Int,
        titleA: String,
        urlA: String,
        imageUrlA: String,
        newsSiteA: String,
        summaryA: String,
        publishedAtA: String,
        updatedAtA: String
    ) = setText(updatedAtA)

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
        idA: Int,
        titleA: String,
        urlA: String,
        imageUrlA: String,
        newsSiteA: String,
        summaryA: String,
        publishedAtA: String,
        updatedAtA: String
    ) {
        Glide.with(context)
            .load(imageUrlA)
            .placeholder(R.drawable.baseline_hourglass_bottom_24)
            .error(com.google.android.material.R.drawable.mtrl_ic_error)
            .into(this)
    }

    override fun map(message: String) = setImageURI("".toUri())
    //endregion
}
