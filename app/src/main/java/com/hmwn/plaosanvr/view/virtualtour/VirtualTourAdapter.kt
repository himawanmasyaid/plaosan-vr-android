package com.hmwn.plaosanvr.view.virtualtour

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hmwn.plaosanvr.common.loadRounded
import com.hmwn.plaosanvr.data.model.VirtualTour
import com.hmwn.plaosanvr.databinding.ItemVirtualTourBinding
import com.hmwn.plaosanvr.databinding.ItemVisitBinding

class VirtualTourAdapter(
    private val onClick: (VirtualTour) -> Unit
): RecyclerView.Adapter<VirtualTourAdapter.ViewHolder>() {

    private val listData: MutableList<VirtualTour> = ArrayList()

    fun resetData(data: List<VirtualTour>) {
        if (listData.isNotEmpty()) {
            listData.clear()
        }
        data.forEach {
            listData.add(it)
            notifyItemInserted(listData.size - 1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemVirtualTourBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listData[position]
        holder.bindTo(item)
    }

    override fun getItemCount() = listData.size

    inner class ViewHolder(val binding: ItemVirtualTourBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTo(visit: VirtualTour) {

            with(binding) {

                tvTitle.text = visit.name
                ivIcon.loadRounded(itemView.context, visit.icon)

                itemView.setOnClickListener {
                    onClick(visit)
                }

            }

        }

    }

}