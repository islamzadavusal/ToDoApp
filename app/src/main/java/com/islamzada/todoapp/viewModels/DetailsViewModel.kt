package com.islamzada.todoapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islamzada.todoapp.entity.Favorite
import com.islamzada.todoapp.entity.Notes
import com.islamzada.todoapp.repo.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel  @Inject constructor () : ViewModel() {
    val title = MutableLiveData<String>()
    val desc = MutableLiveData<String>()


}
