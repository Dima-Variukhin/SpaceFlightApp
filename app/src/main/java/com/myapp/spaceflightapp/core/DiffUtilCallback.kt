package com.myapp.spaceflightapp.core

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback<T : Comparing<T>>(
    private val odlList: List<T>,
    private val newList: List<T>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = odlList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        odlList[oldItemPosition].same(newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        odlList[oldItemPosition].sameContent(newList[newItemPosition])
}