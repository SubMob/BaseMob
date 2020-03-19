package mustafaozhan.github.com.basemob.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlin.properties.Delegates

/**
 * Created by Mustafa Ozhan on 2018-07-12.
 */
abstract class BaseRecyclerViewAdapter<T, TViewBinding : ViewBinding> :
    RecyclerView.Adapter<BaseViewHolder<T, TViewBinding>>(),
    AutoUpdatableAdapter {

    private var list: MutableList<T> by Delegates.observable(mutableListOf()) { _, old, new ->
        autoNotify(old, new) { o, n -> o == n }
    }

    lateinit var onItemClickListener: ((T, TViewBinding, Int) -> Unit)
    lateinit var onItemLongClickListener: ((T, TViewBinding) -> Boolean)

    override fun onBindViewHolder(
        holder: BaseViewHolder<T, TViewBinding>,
        position: Int
    ) = with(holder) {
        bindItem(list[position])
    }

    override fun getItemCount() = list.size

    fun refreshList(list: MutableList<T>) {
        this.list = list
    }
}
