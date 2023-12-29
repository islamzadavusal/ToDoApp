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

    val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val note = arguments?.getString("note")

        viewModel.note.value = note

        return binding.root
    }
}