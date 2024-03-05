package app.ds3wiki.items

import app.ds3wiki.R

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

typealias ItemViewHolder = ItemAdapter.ItemViewHolder

class ItemAdapter : Adapter<ItemViewHolder>() {
    lateinit var onItemSelected: (Item) -> Unit

    private val itemRepository = ItemRepository.getInstance()

    inner class ItemViewHolder(internal val view: View) : ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder = ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row_layout, parent, false))

    override fun getItemCount(): Int = itemRepository.repositorySize

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ): Unit = holder.view.run {
        setOnClickListener {
            onItemSelected(itemRepository.getItem(position))
        }
    }
}