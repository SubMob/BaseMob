package com.github.mustafaozhan.basemob.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.github.mustafaozhan.basemob.viewholder.BaseDBViewHolder

abstract class BaseDBRecyclerViewAdapter<T, TDataBinding : ViewDataBinding>(
    itemDiffer: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseDBViewHolder<T, TDataBinding>>(itemDiffer) {
    override fun onBindViewHolder(
        holder: BaseDBViewHolder<T, TDataBinding>,
        position: Int
    ) = with(holder) {
        onItemBind(getItem(position))
        itemBinding.executePendingBindings()
    }
}
