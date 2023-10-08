package com.tuf2000m.energymeter.presentation.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tuf2000m.energymeter.data.model.meterdata.Data
import com.tuf2000m.energymeter.databinding.ItemEnergycellBinding

class HomeAdapter(
    private var items: List<Data>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(val viewHolder: ItemEnergycellBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemEnergycellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.viewHolder.tvVariable.text = item.variableName
        holder.viewHolder.tvVariablevalue.text = item.value.toString()
        holder.viewHolder.tvVariablcategory.text = item.category
        holder.viewHolder.tvVariableunit.text = item.unit
        listener.onItemClick(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setContentData(data: List<Data>) {
        items = data
        Log.d("TAG", "setcontentDataSize: ${items.size}")
        notifyDataSetChanged()
    }
}