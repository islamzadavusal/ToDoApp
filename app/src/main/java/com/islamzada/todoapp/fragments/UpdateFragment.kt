package com.islamzada.todoapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.islamzada.todoapp.R
import com.islamzada.todoapp.databinding.FragmentUpdateBinding
import com.islamzada.todoapp.viewModels.UpdateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater)

        binding.backUpdate.setOnClickListener {
            requireActivity().onBackPressed()
        }


        return binding.root
    }
}