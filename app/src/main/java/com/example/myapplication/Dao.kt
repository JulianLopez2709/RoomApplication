package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addData(notes: Notes)
}