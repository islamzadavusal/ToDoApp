package com.islamzada.todoapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.islamzada.todoapp.entity.Notes

@Dao
interface NotesDao {

    @Insert
    suspend fun insert(notes: Notes)

    @Query("SELECT * FROM notes")
    fun getAll() : LiveData<List<Notes>>

    @Delete
    suspend fun delete(notes: Notes)

    @Update
    suspend fun update(notes:Notes)

}