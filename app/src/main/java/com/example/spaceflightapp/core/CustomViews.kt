package com.example.spaceflightapp.core

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.spaceflightapp.R
import com.example.spaceflightapp.data.favorites.cache.FavoriteDb
import io.realm.Realm

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
        updatedAt: String,
        data: String
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
        updatedAt: String,
        data: String
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
        updatedAt: String,
        data: String
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
        updatedAt: String,
        data: String
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
        updatedAt: String,
        data: String
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
        updatedAt: String,
        data: String
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
        updatedAt: String,
        data: String
    ) {
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.baseline_hourglass_bottom_24)
            .error(R.drawable.outline_refresh_24)
            .optionalCenterCrop()
            .into(this)
    }

    override fun map(message: String) = setImageURI("".toUri())
    //endregion
}

class CustomTextViewUpdatedData : AppCompatTextView, AdapterNewsMapper<Unit> {
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
        updatedAt: String,
        data: String
    ) = setText(data)

    override fun map(message: String) = setText(message)
    //endregion
}


class CustomFavorite : AppCompatImageButton, AdapterNewsMapper<Unit> {
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
        updatedAt: String,
        data: String

    ) = if (findRealmObject(Realm.getDefaultInstance(), id) == id)
        setImageResource(R.drawable.outline_favorite_black_24)
    else
        setImageResource(R.drawable.outline_favorite_border_black_24)

    private fun findRealmObject(realm: Realm, id: Int) =
        realm.where(FavoriteDb::class.java).equalTo("id", id).findFirst()?.id

    override fun map(message: String) = setImageResource(0)
    //endregion
}
