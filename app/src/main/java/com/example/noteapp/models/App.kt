package com.example.noteapp.models

import android.app.Application
import androidx.room.Room
import com.example.noteapp.db.personDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        personDatabase = Room.databaseBuilder(
            this.applicationContext,
            personDatabase::class.java,
            "car_database"
        ).allowMainThreadQueries().build()
    }
    companion object{
        lateinit var personDatabase: personDatabase
    }
}