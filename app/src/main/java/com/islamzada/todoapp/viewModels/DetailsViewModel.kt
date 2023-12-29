package com.islamzada.todoapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel  @Inject constructor () : ViewModel() {
    val note = MutableLiveData<String>()
}