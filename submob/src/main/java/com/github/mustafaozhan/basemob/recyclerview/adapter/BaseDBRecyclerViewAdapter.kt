package com.github.mustafaozhan.basemob.recyclerview.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.github.mustafaozhan.basemob.recyclerview.viewholder.BaseDBViewHolder

abstract class BaseDBRecyclerViewAdapter<T>(itemDiffer: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, BaseDBViewHolder<T>>(itemDiffer) {

    override fun onBindViewHolder(holder: BaseDBViewHolder<T>, position: Int) {
        holder.onItemBind(getItem(position))
        holder.holderBinding.executePendingBindings()
    }

    fun getItemAtPosition(position: Int): T = getItem(position)
        ?: throw IllegalStateException("Invalid position")
}
