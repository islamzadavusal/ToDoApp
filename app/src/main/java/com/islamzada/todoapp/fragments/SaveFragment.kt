package com.islamzada.todoapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.islamzada.todoapp.R
import com.islamzada.todoapp.databinding.FragmentSaveBinding
import com.islamzada.todoapp.entity.Notes
import com.islamzada.todoapp.viewModels.SaveViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class SaveFragment : Fragment() {
    private lateinit var binding: FragmentSaveBinding

    private val saveViewModel: SaveViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaveBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = saveViewModel


        binding.buttonSave.setOnClickListener {

            saveViewModel.date.value = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())

            saveViewModel.time.value = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

            if (saveViewModel.isInputValid()) {
                val note = Notes(
                    0,
                    saveViewModel.title.value.orEmpty(),
                    saveViewModel.desc.value.orEmpty(),
                    saveViewModel.date.value.orEmpty(),
                    saveViewModel.time.value.orEmpty()
                )

                saveViewModel.insert(note)
                Toast.makeText(requireContext(), getString(R.string.notSaved), Toast.LENGTH_SHORT).show()

               resetFragment()

            } else {
                Toast.makeText(requireContext(), getString(R.string.error), Toast.LENGTH_SHORT).show()
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