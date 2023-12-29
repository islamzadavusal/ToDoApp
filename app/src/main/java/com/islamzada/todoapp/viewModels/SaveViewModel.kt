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
class SaveViewModel @Inject constructor (var repository: NotesRepository): ViewModel() {

    val title = MutableLiveData<String>()
    val desc = MutableLiveData<String>()

    fun isInputValid(): Boolean {
        val title = title.value.orEmpty()
        val desc = desc.value.orEmpty()

        return title.isNotEmpty() && desc.isNotEmpty()
    }

    fun insert(note: Notes) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }

}