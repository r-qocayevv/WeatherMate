package com.revan.weathermate.presentation.fragment.searchLocation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.revan.weathermate.R
import com.revan.weathermate.databinding.FragmentSearchLocationBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchLocationFragment() : Fragment() {
    private var _binding: FragmentSearchLocationBinding? = null
    val binding get() = _binding!!
    private val viewModel : SearchLocationViewModel by viewModels()
    private val adapter = LocationAdapter(emptyList())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchLocationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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

