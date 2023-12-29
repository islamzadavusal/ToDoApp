package com.islamzada.todoapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islamzada.todoapp.entity.Notes
import com.islamzada.todoapp.repo.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveNoteViewModel @Inject constructor (var repository: NotesRepository): ViewModel() {

    val note = MutableLiveData<String>()

    fun isInputValid(): Boolean {
        val note = note.value.orEmpty()

        return note.isNotEmpty()
    }

    fun insert(note: Notes) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }

}