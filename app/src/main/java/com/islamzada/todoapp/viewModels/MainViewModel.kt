package com.islamzada.todoapp.viewModels

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islamzada.todoapp.entity.Favorite
import com.islamzada.todoapp.entity.Notes
import com.islamzada.todoapp.repo.FavoriteRepository
import com.islamzada.todoapp.repo.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (var repository: NotesRepository, var repositoryFav: FavoriteRepository): ViewModel() {


    fun getAllData(): LiveData<List<Notes>> {
        return repository.getAll()
    }

    fun delete(note: Notes) {
        viewModelScope.launch {
            repository.delete(note)

        }
    }

    fun insertToFav(fav: Favorite) {
        viewModelScope.launch {
            repositoryFav.insertToFav(fav)
        }
    }

}
