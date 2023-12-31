package com.islamzada.todoapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.islamzada.todoapp.R
import com.islamzada.todoapp.databinding.FragmentDetailsBinding
import com.islamzada.todoapp.viewModels.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = detailsViewModel

        binding.backDetails.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val title = arguments?.getString("title")
        val desc = arguments?.getString("desc")


        detailsViewModel.title.value = title
        detailsViewModel.desc.value = desc

        return binding.root
    }
}