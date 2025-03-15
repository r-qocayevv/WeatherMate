package com.revan.weathermate.presentation.fragment.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revan.weathermate.R
import com.revan.weathermate.databinding.ViewpagerLayoutBinding

class ViewPagerAdapter() : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {
    val imageList = listOf(
        R.drawable.night_moon,
        R.drawable.sunny,
        R.drawable.rainy,
        R.drawable.partly_cloudy_with_sun
    )

    inner class ViewHolder(val binding: ViewpagerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(ViewpagerLayoutBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding

        val currentImage = imageList[position]
        binding.viewpagerImage.setImageResource(currentImage)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}