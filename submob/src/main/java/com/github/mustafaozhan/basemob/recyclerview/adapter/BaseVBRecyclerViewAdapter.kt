package com.github.mustafaozhan.basemob.recyclerview.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.github.mustafaozhan.basemob.recyclerview.AutoUpdatableAdapter
import com.github.mustafaozhan.basemob.recyclerview.viewholder.BaseVBViewHolder
import kotlin.properties.Delegates

/**
 * Created by Mustafa Ozhan on 2018-07-12.
 */
abstract class BaseVBRecyclerViewAdapter<T, TViewBinding : ViewBinding> :
    RecyclerView.Adapter<BaseVBViewHolder<T, TViewBinding>>(),
    AutoUpdatableAdapter {

    private var list: MutableList<T> by Delegates.observable(mutableListOf()) { _, old, new ->
        autoNotify(old, new) { o, n -> o == n }
    }

    lateinit var onItemClickListener: ((T, TViewBinding, Int) -> Unit)
    lateinit var onItemLongClickListener: ((T, TViewBinding) -> Boolean)

    override fun onBindViewHolder(
        holder: BaseVBViewHolder<T, TViewBinding>,
        position: Int
    ) = with(holder) {
        bindItem(list[position])
    }

    override fun getItemCount() = list.size

    fun refreshList(list: MutableList<T>) {
        this.list = list
    }
}
