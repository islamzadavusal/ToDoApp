package com.islamzada.todoapp.di

import com.islamzada.todoapp.repo.FavoriteRepository
import com.islamzada.todoapp.repo.FavoriteRepositoryInterface
import com.islamzada.todoapp.repo.NotesRepository
import com.islamzada.todoapp.repo.NotesRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn (SingletonComponent::class)
abstract class RepositoryModel {

    @Binds
    abstract fun bindNoteRepository(nrp : NotesRepository) : NotesRepositoryInterface

    @Binds
    abstract fun bindFavoriteRepository(frp : FavoriteRepository) : FavoriteRepositoryInterface

}