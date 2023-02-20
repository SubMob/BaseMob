/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.basemob.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@Suppress("unused")
abstract class BaseDBRecyclerViewAdapter<T>(
    itemDiffer: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseDBRecyclerViewAdapter.BaseDBViewHolder<T>>(itemDiffer) {
    override fun onBindViewHolder(
        holder: BaseDBViewHolder<T>,
        position: Int
    ) = with(holder) {
        onItemBind(getItem(position))
        itemBinding.executePendingBindings()
    }

    abstract class BaseDBViewHolder<in T>(
        val itemBinding: ViewDataBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        abstract fun onItemBind(item: T)
    }
}
