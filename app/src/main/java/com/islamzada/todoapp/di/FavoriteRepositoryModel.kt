package com.islamzada.todoapp.di

import com.islamzada.todoapp.repo.FavoriteRepository
import com.islamzada.todoapp.repo.FavoriteRepositoryInterface
import com.islamzada.todoapp.repo.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoriteRepositoryModel {

    @Binds
    abstract fun bindRepository(frp : FavoriteRepository) : FavoriteRepositoryInterface

}