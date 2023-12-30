package com.islamzada.todoapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.islamzada.todoapp.R
import com.islamzada.todoapp.databinding.FragmentUpdateBinding
import com.islamzada.todoapp.viewModels.UpdateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding
    private val updateViewModel: UpdateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = updateViewModel

        val args: UpdateFragmentArgs by navArgs()
        val note = args.note

        updateViewModel.title.value = (note.title)
        updateViewModel.desc.value = (note.desc)

        binding.backUpdate.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.buttonUpdate.setOnClickListener {
            val updatedNote = note.copy(
                title = binding.editTextTitle.text.toString(),
                desc = binding.editTextDesc.text.toString()
            )
            updateViewModel.update(updatedNote)
            requireActivity().onBackPressed()
        }

        return binding.root
    }
}
