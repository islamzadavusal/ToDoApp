package com.islamzada.todoapp.di

import android.content.Context
import androidx.room.Room
import com.islamzada.todoapp.dao.FavoriteDao
import com.islamzada.todoapp.dao.NotesDao
import com.islamzada.todoapp.database.FavoriteDatabase
import com.islamzada.todoapp.database.NotesDatabase
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
    fun provideNotesDatabase(@ApplicationContext context: Context): NotesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NotesDatabase::class.java,
            "notes_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteDatabase(@ApplicationContext context: Context): FavoriteDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            FavoriteDatabase::class.java,
            "favorite_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNotesDao(notesDatabase: NotesDatabase): NotesDao {
        return notesDatabase.noteDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(favoriteDatabase: FavoriteDatabase): FavoriteDao {
        return favoriteDatabase.favoriteDao()
    }
}