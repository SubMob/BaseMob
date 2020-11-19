/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.basemob.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseVBRecyclerViewAdapter<T, TViewBinding : ViewBinding>(
    itemDiffer: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseVBRecyclerViewAdapter.BaseVBViewHolder<T, TViewBinding>>(itemDiffer) {
    override fun onBindViewHolder(
        holder: BaseVBViewHolder<T, TViewBinding>,
        position: Int
    ) = with(holder) {
        onItemBind(getItem(position))
    }

    abstract class BaseVBViewHolder<out T, TViewBinding : ViewBinding>(
        itemBinding: TViewBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        abstract fun onItemBind(item: @UnsafeVariance T)
    }
}
