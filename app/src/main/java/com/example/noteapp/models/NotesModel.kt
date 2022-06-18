package com.example.noteapp.models

import androidx.room.Entity

@Entity(tableName = "person_table")
data class NotesModel (
    var personItemText :String,
    var personItem : String
        )