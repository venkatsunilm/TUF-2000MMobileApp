package com.tuf2000m.energymeter.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tuf2000m.energymeter.data.remote.model.recent.Recents
import com.tuf2000m.energymeter.databinding.ItemRecyclerRecentsBinding

interface OnItemClickListener {
    fun onItemClick(
        position: Int
    )
}

class RecentAdapter(
    private val items: List<Recents.Recent>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecentAdapter.ViewHolder>() {

    class ViewHolder(val viewHolder: ItemRecyclerRecentsBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRecyclerRecentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listener.onItemClick(position)
        holder.viewHolder.tvRecent.text = items[position].timestamp


    }


    override fun getItemCount(): Int {
        return items.size
    }
}