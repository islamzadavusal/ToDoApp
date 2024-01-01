package com.islamzada.todoapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.islamzada.todoapp.R
import com.islamzada.todoapp.databinding.FragmentUpdateBinding
import com.islamzada.todoapp.viewModels.UpdateViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
        updateViewModel.date.value = (note.date)
        updateViewModel.time.value = (note.time)

        binding.backUpdate.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.buttonUpdate.setOnClickListener {
            updateViewModel.date.value = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
            updateViewModel.time.value = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

            if (updateViewModel.isInputValid()) {
            val updatedNote = note.copy(
                title = updateViewModel.title.value.orEmpty(),
                desc = updateViewModel.desc.value.orEmpty(),
                date = updateViewModel.date.value.orEmpty(),
                time = updateViewModel.time.value.orEmpty()
            )
            updateViewModel.update(updatedNote)
            requireActivity().onBackPressed()
            Toast.makeText(requireContext(), getString(R.string.notUpdated), Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(requireContext(), getString(R.string.error), Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}
