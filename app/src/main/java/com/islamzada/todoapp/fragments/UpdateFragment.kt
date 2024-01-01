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


            val updatedNote = note.copy(
                title = binding.editTextTitle.text.toString(),
                desc = binding.editTextDesc.text.toString(),
                date = updateViewModel.date.value.toString(),
                time = updateViewModel.time.value.toString()
            )
            updateViewModel.update(updatedNote)
            requireActivity().onBackPressed()
        }

        return binding.root
    }
}
