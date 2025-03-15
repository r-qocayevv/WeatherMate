package com.revan.weathermate.presentation.fragment.guide

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.revan.weathermate.R
import com.revan.weathermate.databinding.FragmentGuideBinding
import com.revan.weathermate.presentation.fragment.main.adapter.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuideFragment : Fragment() {
    private var _binding : FragmentGuideBinding? = null
    private val binding get() = _binding!!
    private val guideViewModel : GuideViewModel by viewModels()
    private lateinit var requestLocationPermission : ActivityResultLauncher<String>
    private var guideNumber = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGuideBinding.inflate(inflater,container,false)
        requestLocationPermissionLauncher()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        checkIsFinishedGuide()
        setViewPagerAdapter()
        changeNextGuide()

        binding.nextButton.setOnClickListener {
            changeNextGuide()
        }

        binding.skipButton.setOnClickListener {
            guideNumber = 4
            changeNextGuide()
        }
    }

    private fun checkIsFinishedGuide() {
        val isFinishedGuide =
            requireActivity().intent.getBooleanExtra("isFinishedGuide", false)
        if (isFinishedGuide) {
            checkLocationPermission()
        }
    }

    private fun checkLocationPermission() {
        val permission = Manifest.permission.ACCESS_FINE_LOCATION
        val isPermissionGranted =
            ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED

        if(isPermissionGranted){
            findNavController().navigate(R.id.action_guideFragment_to_weatherInfoFragment)
        }else {
            findNavController().navigate(R.id.action_guideFragment_to_searchLocationFragment)
        }
    }

    private fun checkLocationPermission(isGranted: Boolean) {
        if(isGranted){
            findNavController().navigate(R.id.action_guideFragment_to_weatherInfoFragment)
        }else {
            findNavController().navigate(R.id.action_guideFragment_to_searchLocationFragment)
        }
    }

    private fun requestLocationPermissionLauncher() {
        requestLocationPermission = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            checkLocationPermission(isGranted)
        }
    }

    private fun finishGuide() {
        guideViewModel.saveData("isFinishedGuide","true")
    }

    private fun changeNextGuide () {
        changeViewPagerImage(guideNumber-1)

        when(guideNumber){
            1 -> {
                binding.titleText.text = getString(R.string.title_1)
                binding.subtitleText.text = getString(R.string.subtitle_1)
                updateProgressBar(25)
            }
            2 -> {
                binding.titleText.text = getString(R.string.title_2)
                binding.subtitleText.text = getString(R.string.subtitle_2)
                updateProgressBar(50)
            }

            3 -> {
                binding.titleText.text = getString(R.string.title_3)
                binding.subtitleText.text = getString(R.string.subtitle_3)
                updateProgressBar(75)

            }

            4 -> {
                updateProgressBar(100)
                binding.titleText.text = getString(R.string.title_4)
                binding.subtitleText.text = getString(R.string.subtitle_4)
                binding.nextButtonImage.setImageResource(R.drawable.icon_confirm)
                binding.skipButton.visibility = View.INVISIBLE
                binding.nextButton.setOnClickListener {
                    requestPermission()
                    finishGuide()
                }
                binding.subtitleText.setOnClickListener {
                    navigateSearchLocationScreen()
                }
            }
        }
        guideNumber++
    }

    private fun navigateSearchLocationScreen() {
        findNavController().navigate(R.id.action_guideFragment_to_searchLocationFragment)
        finishGuide()
    }

    private fun requestPermission() {
        requestLocationPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun changeViewPagerImage(imageNumber : Int){
        binding.viewPager.setCurrentItem(imageNumber, true)
    }

    private fun updateProgressBar (progress : Int){
        binding.progressBar.progress = progress
    }

    private fun setViewPagerAdapter(){
        binding.viewPager.adapter = ViewPagerAdapter()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}