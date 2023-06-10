package com.hmwn.plaosanvr.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hmwn.plaosanvr.common.loadRounded
import com.hmwn.plaosanvr.data.model.Panoramic
import com.hmwn.plaosanvr.databinding.ItemPanoramicPhotoBinding

class PanoramicAdapter(
    private val onClick: (Panoramic) -> Unit
): RecyclerView.Adapter<PanoramicAdapter.ViewHolder>() {

    private val listData: MutableList<Panoramic> = ArrayList()

    fun resetData(data: List<Panoramic>) {
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
            ItemPanoramicPhotoBinding.inflate(
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

    inner class ViewHolder(val binding: ItemPanoramicPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTo(panoramic: Panoramic) {

            with(binding) {
                tvName.text = panoramic.name
                ivBackground.loadRounded(itemView.context, panoramic.picture)

                itemView.setOnClickListener {
                    onClick(panoramic)
                }

            }
        }

    }

}