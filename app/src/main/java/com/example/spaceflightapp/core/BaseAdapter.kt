package com.example.spaceflightapp.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceflightapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

abstract class BaseAdapter<E : FromUi<E>, T : BaseViewHolder<E>> :
    RecyclerView.Adapter<T>(), ListMapper<E> {
    protected val list = ArrayList<E>()

    override fun map(data: List<E>) {
        val diffCallback = DiffUtilCallback(list, data)
        val result = DiffUtil.calculateDiff(diffCallback)
        list.clear()
        list.addAll(data)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bind(list[position])
    }

    protected fun Int.makeView(parent: ViewGroup) =
        LayoutInflater.from(parent.context).inflate(this, parent, false)
}

abstract class BaseViewHolder<E : FromUi<E>>(view: View) :
    RecyclerView.ViewHolder(view) {
    open fun bind(item: E) {}


    class FullScreenProgress<E : FromUi<E>>(view: View) : BaseViewHolder<E>(view)

    class Fail<E : FromUi<E>>(
        view: View,
        private val retry: Retry
    ) : BaseViewHolder<E>(view) {
        private val message = itemView.findViewById<CustomTextView>(R.id.messageTextView)
        private val button = itemView.findViewById<FloatingActionButton>(R.id.tryAgainButton)
        override fun bind(item: E) {
            item.map(message)
            button.setOnClickListener {
                retry.tryAgain()
            }
        }
    }
}

interface ComparableTextMapper<T : ComparableTextMapper<T>> : Abstract.Object<Unit, TextMapper>,
    Comparing<T>

interface FromUi<T : FromUi<T>> : Abstract.Object<Unit, AdapterArticleMapper<Unit>>, Comparing<T>


interface AdapterArticleMapper<T> : Abstract.Mapper {
    fun map(
        idA: Int,
        titleA: String,
        urlA: String,
        imageUrlA: String,
        newsSiteA: String,
        summaryA: String,
        publishedAtA: String,
        updatedAtA: String
    ): T

    fun map(message: String)
}


interface TextMapper : Abstract.Mapper.Data<String, Unit>

interface ClickListener<T> {
    fun click(item: T)
}