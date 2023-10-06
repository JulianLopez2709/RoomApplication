package com.example.myapplication

class Repository(private val dao:Dao) {
    suspend fun addData(notes:Notes){
        dao.addData(notes)
    }
}