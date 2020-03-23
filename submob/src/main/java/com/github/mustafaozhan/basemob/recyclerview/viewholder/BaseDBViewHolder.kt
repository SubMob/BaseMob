package com.github.mustafaozhan.basemob.recyclerview.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseDBViewHolder<T>(val holderBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(holderBinding.root) {

    abstract fun onItemBind(item: T)
}
