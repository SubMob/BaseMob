/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.basemob.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseVBViewHolder<out T, TViewBinding : ViewBinding>(
    protected val itemBinding: TViewBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    abstract fun onItemBind(item: @UnsafeVariance T)
}
