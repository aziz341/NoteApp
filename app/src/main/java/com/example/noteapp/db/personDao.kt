package com.example.noteapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.models.NotesModel


@Dao
interface personDao {

    @Query("select * from car_table" )
    fun getAllCars(): MutableList<NotesModel>

    @Update
    fun updateCar(person: NotesModel)
    @Delete
    fun deleteCar(person: NotesModel)
}
