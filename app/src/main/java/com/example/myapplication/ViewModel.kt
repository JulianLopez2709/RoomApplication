package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application):AndroidViewModel(application) {
    private val repository :Repository
    val allNotes : LiveData<List<Notes>>

    init{
        val dao = Database.getDatabase(application).noteDao()
        repository= Repository(dao)
        allNotes = repository.getAllNotes
        println("Notes: ${repository.getAllNotes}")
    }

    fun addData(notes:Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addData(notes)
        }
    }
}