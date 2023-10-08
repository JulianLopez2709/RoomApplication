package com.example.myapplication

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addData(notes: Notes)

    @Query("SELECT * FROM notes ORDER BY id ASC")
    fun getAllNotes():LiveData<List<Notes>>

    @Update()
    fun updateNote(notes: Notes)
}