package com.github.mustafaozhan.basemob.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.github.mustafaozhan.basemob.view.BaseItemView
import com.github.mustafaozhan.basemob.viewholder.BaseVBViewHolder

/**
 * Created by Mustafa Ozhan on 2018-07-12.
 */
abstract class BaseVBRecyclerViewAdapter<T, TViewBinding : ViewBinding>(
    itemView: BaseItemView,
    itemDiffer: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseVBViewHolder<T, TViewBinding>>(itemDiffer) {
    override fun onBindViewHolder(
        holder: BaseVBViewHolder<T, TViewBinding>,
        position: Int
    ) = with(holder) {
        bindItem(getItem(position))
    }
}
