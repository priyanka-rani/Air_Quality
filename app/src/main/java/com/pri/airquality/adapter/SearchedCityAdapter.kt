package com.pri.airquality.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pri.airquality.databinding.ItemCityBinding
import com.pri.airquality.model.Station
import com.pri.airquality.ui.search.SearchFragmentDirections

class SearchedCityAdapter :
    ListAdapter<Station, SearchedCityAdapter.ViewHolder>(StationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemCityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener { view ->
                val item = getItem(adapterPosition)
                view.findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToDetailsFragment(
                        title = item.toString(),
                        id = item.id
                    )
                )
            }
        }

        fun bind(station: Station) {
            binding.tvCityName.text = station.toString()
        }
    }
}

private class StationDiffCallback : DiffUtil.ItemCallback<Station>() {

    override fun areItemsTheSame(oldItem: Station, newItem: Station): Boolean {
        return oldItem.countryCode == newItem.countryCode
    }

    override fun areContentsTheSame(oldItem: Station, newItem: Station): Boolean {
        return oldItem == newItem
    }
}
