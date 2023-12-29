package com.islamzada.todoapp.viewModels

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
class FavoriteViewModel @Inject constructor (var repository: FavoriteRepository): ViewModel() {


    fun getAllDataFav(): LiveData<List<Favorite>> {
        return repository.getAllFav()
    }

    fun deleteFav(fav: Favorite) {
        viewModelScope.launch {
            repository.deleteFav(fav)

        }
    }

}