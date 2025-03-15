package com.revan.weathermate.presentation.fragment.searchLocation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.revan.weathermate.databinding.LocationRowLayoutBinding
import com.revan.weathermate.domain.model.LocationItem
import com.revan.weathermate.presentation.fragment.searchLocation.SearchLocationFragmentDirections
import com.revan.weathermate.util.DiffUtilClass

class LocationAdapter(var locations: List<LocationItem>) :
    RecyclerView.Adapter<LocationAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(LocationRowLayoutBinding.inflate(layoutInflater, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val binding = holder.binding
        val currentLocation = locations[position]

        binding.displayNameText.text = currentLocation.displayName
        binding.latAndLongText.text = "${currentLocation.lat}, ${currentLocation.lon}"

        binding.layout.setOnClickListener {
            navigateWeatherInfoFragment(
                lat = currentLocation.lat.toFloat(),
                long = currentLocation.lon.toFloat(),
                it
            )
        }
    }

    private fun navigateWeatherInfoFragment(lat: Float, long: Float, view: View) {
        val action =
            SearchLocationFragmentDirections.actionSearchLocationFragmentToWeatherInfoFragment(
                lat,
                long
            )
        view.findNavController().navigate(action)
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    inner class ViewHolder(val binding: LocationRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setData(newLocations: List<LocationItem>) {
        val diffUtil = DiffUtilClass(locations, newLocations)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        locations = newLocations
        diffResults.dispatchUpdatesTo(this)

    }
}