package com.revan.weathermate.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import android.Manifest
import androidx.activity.result.ActivityResultLauncher
import com.revan.weathermate.R
import com.revan.weathermate.databinding.FragmentGuideBinding
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
        initializeRequestLocationPermission()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setStatusBarColor(R.color.bright_sky_blue)
        setNavigationBarColor(R.color.white)
        setViewPagerAdapter()
        changeNextGuide()
        checkIsFinishedGuide()

        binding.nextButton.setOnClickListener {
            changeNextGuide()
        }

        binding.skipButton.setOnClickListener {
            skipGuide()
        }
    }

    fun checkIsFinishedGuide() {
        val isFinishedGuide = requireActivity().intent.getBooleanExtra("isFinishedGuide",false)
        if (isFinishedGuide){
            skipGuide()
        }
    }

    fun skipGuide() {
        guideViewModel.saveData("isFinishedGuide","true")
        findNavController().navigate(R.id.action_guideFragment_to_weatherInfoFragment)
    }

    fun setViewPagerAdapter(){
        binding.viewPager.adapter = ViewPagerAdapter()
    }

    @Suppress("DEPRECATION")
    fun setStatusBarColor(color: Int) {
        activity?.window?.statusBarColor = resources.getColor(color)
    }

    @Suppress("DEPRECATION")
    fun setNavigationBarColor(color: Int) {
        activity?.window?.navigationBarColor = resources.getColor(color)
    }

    fun changeNextGuide () {
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
                binding.titleText.text = getString(R.string.title_4)
                binding.subtitleText.text = getString(R.string.subtitle_4)
                binding.nextButtonImage.setImageResource(R.drawable.icon_confirm)
                updateProgressBar(100)
                requestPermission()
            }
        }
        guideNumber++
    }

    private fun requestPermission() {
        binding.nextButton.setOnClickListener {
            requestLocationPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            skipGuide()
        }
    }

    private fun initializeRequestLocationPermission() {
        requestLocationPermission = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            checkLocationPermission(isGranted)
        }
    }

    private fun checkLocationPermission(isGranted: Boolean) {
        if(isGranted){

        }else {

        }
    }

    fun changeViewPagerImage(imageNumber : Int){
        binding.viewPager.setCurrentItem(imageNumber, true)
    }

    fun updateProgressBar (progress : Int){
        binding.progressBar.progress = progress
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}



