package com.github.mustafaozhan.basemob.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseDBViewHolder<out T, TDataBinding : ViewDataBinding>(
    val itemBinding: TDataBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    abstract fun onItemBind(item: @UnsafeVariance T)
}
