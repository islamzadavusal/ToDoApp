package com.islamzada.todoapp.repo

import androidx.lifecycle.LiveData
import com.islamzada.todoapp.dao.FavoriteDao
import com.islamzada.todoapp.dao.NotesDao
import com.islamzada.todoapp.entity.Favorite
import com.islamzada.todoapp.entity.Notes
import javax.inject.Inject

interface FavoriteRepositoryInterface {


}

class FavoriteRepository @Inject constructor(private val favoriteDao : FavoriteDao) : FavoriteRepositoryInterface{

    suspend fun insertToFav (fav: Favorite) {
        favoriteDao.insertToFav(fav)
    }


    fun getAllFav() : LiveData<List<Favorite>> {
        return favoriteDao.getAllFavorite()
    }


    suspend fun deleteFav (fav: Favorite) {
        favoriteDao.deleteFavorite(fav)
    }

}