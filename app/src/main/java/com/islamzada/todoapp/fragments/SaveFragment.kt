package com.islamzada.todoapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.islamzada.todoapp.databinding.FragmentSaveBinding
import com.islamzada.todoapp.entity.Notes
import com.islamzada.todoapp.viewModels.SaveViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveFragment : Fragment() {
    private lateinit var binding: FragmentSaveBinding

    val viewModel: SaveViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaveBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


        binding.buttonSave.setOnClickListener {
            if (viewModel.isInputValid()) {
                val note = Notes(
                    0,
                    viewModel.title.value.orEmpty(),
                    viewModel.desc.value.orEmpty()
                )
                viewModel.insert(note)
                Toast.makeText(requireContext(), "Note Saved", Toast.LENGTH_SHORT).show()

               resetFragment()

            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.backSave.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }

    private fun resetFragment() {
        binding.editTextTitle.text = null
        binding.editTextDesc.text = null

    }

    override fun onDestroyView() {
        super.onDestroyView()
        resetFragment()
    }
}