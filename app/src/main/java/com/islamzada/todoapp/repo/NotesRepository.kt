package com.islamzada.todoapp.repo

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import com.islamzada.todoapp.dao.NotesDao
import com.islamzada.todoapp.entity.Favorite
import com.islamzada.todoapp.entity.Notes
import javax.inject.Inject

interface NotesRepositoryInterface {


}

class NotesRepository @Inject constructor(private val notesDao : NotesDao) : NotesRepositoryInterface{
    suspend fun insert (notes: Notes) {
        notesDao.insert(notes)
    }


    fun getAll() : LiveData<List<Notes>> {
        return notesDao.getAll()
    }

    suspend fun delete (notes: Notes) {
        notesDao.delete(notes)
    }


    suspend fun update (notes: Notes) {
        notesDao.update(notes)
    }
}