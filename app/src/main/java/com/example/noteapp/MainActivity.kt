package com.example.noteapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.noteapp.adapter.Adapter
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.databinding.DialogBinding
import com.example.noteapp.db.personDao
import com.example.noteapp.models.App
import com.example.noteapp.models.NotesModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    private val binding :ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val adapter:Adapter by lazy {
        Adapter()
    }
    private val dao: personDao by lazy {
        App.personDatabase.personDao()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    override fun showCustomAlertDialog(position: Int, person: NotesModel) {
        val dialogBinding = DialogBinding.inflate(layoutInflater)
        dialogBinding.apply {
            val dialog = AlertDialog.Builder(this@MainActivity)
                .setTitle("Удалить или обновить")
                .setView(root)
                .setPositiveButton("Обновить", null)
                .setNegativeButton("Удалить", null)
                .setNeutralButton("Отменить", null)
                .create()
            dialog.setOnShowListener {
                carModel.setText(person.personItemText)
                carPrice.setText(person.personItem)

                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                    val newName = carModel.text.toString()
                    val newPrice = carPrice.text.toString()
                    val newCar = NotesModel(personItem = newName, personItemText = newPrice)
                    dao.updateCar(newCar)
                    adapter.updateCar(position = position,newPerson = person,newName = person)
                    dialog.dismiss()
                }
                dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener {
                    dao.deleteCar(person = person)
                    adapter.deleteCar(position = position)
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.mainRv.adapter = adapter
        loadData()

        binding.addBtnNext.setOnClickListener {
            startActivity(Intent(this,NoteActivity::class.java))
        }
    }
    private fun loadData() {
        val sharedPref = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPref.getString("MySaveData", null)
        val type = object : TypeToken<MutableList<NotesModel>>() {}.type
        val loadNotesList : MutableList<NotesModel>? = gson.fromJson(json, type)


        adapter.notesList = loadNotesList ?: listOf()
    }

}