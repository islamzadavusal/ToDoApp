package com.islamzada.todoapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islamzada.todoapp.entity.Notes
import com.islamzada.todoapp.repo.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor (var repository: NotesRepository): ViewModel() {

    val title = MutableLiveData<String>()
    val desc = MutableLiveData<String>()

    var time = MutableLiveData<String>()
    var date = MutableLiveData<String>()

    fun isInputValid(): Boolean {
        val title = title.value.orEmpty()
        val desc = desc.value.orEmpty()

        return title.isNotEmpty() && desc.isNotEmpty()
    }


    fun update(note: Notes) {
        viewModelScope.launch {
            repository.update(note)

        }
    }

}
