/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.basemob.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseVBRecyclerViewAdapter<T>(
    itemDiffer: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseVBRecyclerViewAdapter.BaseVBViewHolder<T>>(itemDiffer) {
    override fun onBindViewHolder(
        holder: BaseVBViewHolder<T>,
        position: Int
    ) = with(holder) {
        onItemBind(getItem(position))
    }

    abstract class BaseVBViewHolder<in T>(
        itemBinding: ViewBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        abstract fun onItemBind(item: T)
    }
}
