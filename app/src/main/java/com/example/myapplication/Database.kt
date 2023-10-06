package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Notes::class],
    version = 1,
    exportSchema = false
)
abstract class Database: RoomDatabase() {

    abstract fun noteDao():Dao

    companion object{
        @Volatile
        private var INSTANCE : com.example.myapplication.Database? = null

        fun getDatabase(context: Context):com.example.myapplication.Database{
            val instance = INSTANCE
            if(instance != null){
                return instance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,com.example.myapplication.Database::class.java,"notes"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}