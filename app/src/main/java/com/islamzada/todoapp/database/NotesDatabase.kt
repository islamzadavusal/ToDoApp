package com.islamzada.todoapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.islamzada.todoapp.dao.NotesDao
import com.islamzada.todoapp.entity.Favorite
import com.islamzada.todoapp.entity.Notes

@Database(entities = [Notes::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NotesDao
}