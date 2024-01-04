package com.islamzada.todoapp.di

import com.islamzada.todoapp.repo.NotesRepository
import com.islamzada.todoapp.repo.NotesRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn (SingletonComponent::class)
abstract class NotesRepositoryModel {

    @Binds
    abstract fun bindRepository(nrp : NotesRepository) : NotesRepositoryInterface

}