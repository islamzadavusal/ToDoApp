package com.islamzada.todoapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.islamzada.todoapp.dao.FavoriteDao
import com.islamzada.todoapp.entity.Favorite

@Database(entities = [Favorite::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}