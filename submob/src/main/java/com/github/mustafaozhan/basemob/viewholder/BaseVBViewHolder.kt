package com.github.mustafaozhan.basemob.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Created by Mustafa Ozhan on 2018-07-12.
 */
abstract class BaseVBViewHolder<out T, TViewBinding : ViewBinding>(
    protected val itemBinding: TViewBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    abstract fun onItemBind(item: @UnsafeVariance T)
}
