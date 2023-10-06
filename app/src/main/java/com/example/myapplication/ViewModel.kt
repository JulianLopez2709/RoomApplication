package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application):AndroidViewModel(application) {
    private val repository :Repository

    init{
        val dao = Database.getDatabase(application).noteDao()
        repository= Repository(dao)
    }

    fun addData(notes:Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addData(notes)
        }
    }
}