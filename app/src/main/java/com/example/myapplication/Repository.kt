package com.example.myapplication

import androidx.lifecycle.LiveData

class Repository(private val dao:Dao) {

    suspend fun addData(notes:Notes){
        dao.addData(notes)
    }

    val getAllNotes:LiveData<List<Notes>> = dao.getAllNotes()
}