package com.revan.weathermate.presentation.fragment.searchLocation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.revan.weathermate.R
import com.revan.weathermate.databinding.FragmentSearchLocationBinding
import com.revan.weathermate.presentation.fragment.searchLocation.adapter.LocationAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchLocationFragment() : Fragment() {
    private var _binding: FragmentSearchLocationBinding? = null
    val binding get() = _binding!!
    private val viewModel : SearchLocationViewModel by viewModels()
    private lateinit var requestLocationPermission : ActivityResultLauncher<String>
    private val adapter = LocationAdapter(emptyList())
    private var toast : Toast? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchLocationBinding.inflate(layoutInflater, container, false)
        requestLocationPermissionLauncher()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showStatusBarAndNavigationBar()
        navigate()
        searchLocation()
        setAdapter()

        viewModel.error.observe(viewLifecycleOwner){
            setErrorMessage(it)
        }

        viewModel.isLoading.observe(viewLifecycleOwner){
            setLoadingVisibility(it)
        }

        viewModel.location.observe (viewLifecycleOwner){
            binding.locationsRV.visibility = View.VISIBLE
            adapter.setData(it)
        }

        binding.myLocation.setOnClickListener {
            requestLocationPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            getUserLocation()
        }
    }

    private fun getUserLocation() {
        toast?.cancel()
        val permission = Manifest.permission.ACCESS_FINE_LOCATION
        val isPermissionGranted =
            ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED
        checkIsPermissionGranted(isPermissionGranted)
    }

    private fun checkIsPermissionGranted(isGranted: Boolean) {
        if (isGranted) {
            findNavController().navigate(R.id.action_searchLocationFragment_to_weatherInfoFragment)
        }else {
            toast = Toast.makeText(requireContext(), "Please grant location permission", Toast.LENGTH_SHORT)
            toast?.show()
        }
    }

    private fun requestLocationPermissionLauncher() {
        requestLocationPermission = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            checkIsPermissionGranted(isGranted)
        }
    }

    private fun showStatusBarAndNavigationBar() {
        ViewCompat.setOnApplyWindowInsetsListener(requireActivity().findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    private fun setErrorMessage(errorMessage: String) {
        if (errorMessage.isNotEmpty()) {
            binding.errorMessageText.visibility = View.VISIBLE
            binding.progressBar2.visibility = View.GONE
            binding.locationsRV.visibility = View.GONE
            binding.errorMessageText.text = errorMessage

        }else {
            binding.errorMessageText.visibility = View.GONE
        }
    }


    private fun setLoadingVisibility(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar2.visibility = View.VISIBLE
            binding.errorMessageText.visibility = View.GONE
            binding.locationsRV.visibility = View.GONE
        } else {
            binding.progressBar2.visibility = View.GONE
        }
    }


    private fun setAdapter() {
        binding.locationsRV.layoutManager = LinearLayoutManager(requireContext())
        binding.locationsRV.adapter = adapter
    }


    private fun searchLocation() {
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {
            }

            override fun onTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isEmpty()){
                    binding.locationsRV.visibility = View.GONE
                    binding.progressBar2.visibility = View.GONE
                    binding.errorMessageText.visibility = View.GONE
                }else {
                    viewModel.getLocations(p0.toString())
                }

            }
        })
    }


    private fun navigate() {
        binding.backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}

