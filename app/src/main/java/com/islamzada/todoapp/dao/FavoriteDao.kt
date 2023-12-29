package com.islamzada.todoapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.islamzada.todoapp.entity.Favorite
import com.islamzada.todoapp.entity.Notes

@Dao
interface FavoriteDao {

    @Insert
    suspend fun insertToFav(fav: Favorite)

    @Query("SELECT * FROM favorite")
    fun getAllFavorite() : LiveData<List<Favorite>>

    @Delete
    suspend fun deleteFavorite(fav: Favorite)

}