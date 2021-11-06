package com.example.spaceflightapp.core

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

//todo make all views custom except image

class CustomTextView : AppCompatTextView, AdapterArticleMapper<Unit> {
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

class CustomTextViewTitle : AppCompatTextView, AdapterArticleMapper<Unit> {
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

class CustomTextViewSummary : AppCompatTextView, AdapterArticleMapper<Unit> {
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

class CustomTextViewNews : AppCompatTextView, AdapterArticleMapper<Unit> {
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

class CustomTextViewPublished : AppCompatTextView, AdapterArticleMapper<Unit> {
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

class CustomTextViewUpdated : AppCompatTextView, AdapterArticleMapper<Unit> {
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
