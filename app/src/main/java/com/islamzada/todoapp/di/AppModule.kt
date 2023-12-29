package com.islamzada.todoapp.di

import android.content.Context
import androidx.room.Room
import com.islamzada.todoapp.dao.NotesDao
import com.islamzada.todoapp.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDao(db: AppDatabase) : NotesDao {
        return db.noteDao()

    }

    @Provides
    @Singleton
    fun provideAppDatabase (@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "notes").build()
    }
}