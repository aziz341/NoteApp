package com.example.noteapp.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.models.NotesModel

@Database(entities = [NotesModel::class], version = 1)
abstract class personDatabase:RoomDatabase() {

    abstract fun personDao(): personDao

}