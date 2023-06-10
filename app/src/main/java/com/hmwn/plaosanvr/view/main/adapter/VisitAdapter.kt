package com.hmwn.plaosanvr.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hmwn.plaosanvr.common.loadRounded
import com.hmwn.plaosanvr.data.model.Panoramic
import com.hmwn.plaosanvr.data.model.VisitPlaosan
import com.hmwn.plaosanvr.databinding.ItemPanoramicPhotoBinding
import com.hmwn.plaosanvr.databinding.ItemVisitBinding

class VisitAdapter(
    private val onClick: (VisitPlaosan) -> Unit
) : RecyclerView.Adapter<VisitAdapter.ViewHolder>() {

    private val listData: MutableList<VisitPlaosan> = ArrayList()

    fun resetData(data: List<VisitPlaosan>) {
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
            ItemVisitBinding.inflate(
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

    inner class ViewHolder(val binding: ItemVisitBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTo(visit: VisitPlaosan) {

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