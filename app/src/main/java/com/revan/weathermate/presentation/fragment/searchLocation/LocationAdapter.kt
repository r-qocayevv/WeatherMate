package com.revan.weathermate.presentation.fragment.searchLocation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.revan.weathermate.databinding.LocationRowLayoutBinding
import com.revan.weathermate.domain.model.LocationItem
import com.revan.weathermate.util.DiffUtilClass

class LocationAdapter(var locations : List<LocationItem>) : RecyclerView.Adapter<LocationAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(LocationRowLayoutBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val binding = holder.binding
        val currentLocation = locations[position]

        binding.displayNameText.text = currentLocation.displayName
        binding.latAndLongText.text = "${currentLocation.lat}, ${currentLocation.lon}"
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    inner class ViewHolder(val binding: LocationRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setData (newLocations : List<LocationItem>){
        val diffUtil = DiffUtilClass(locations, newLocations)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        locations = newLocations
        diffResults.dispatchUpdatesTo(this)

    }
}