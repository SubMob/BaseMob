/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.basemob.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.github.mustafaozhan.basemob.viewholder.BaseVBViewHolder

abstract class BaseVBRecyclerViewAdapter<T, TViewBinding : ViewBinding>(
    itemDiffer: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseVBViewHolder<T, TViewBinding>>(itemDiffer) {
    override fun onBindViewHolder(
        holder: BaseVBViewHolder<T, TViewBinding>,
        position: Int
    ) = with(holder) {
        onItemBind(getItem(position))
    }
}
