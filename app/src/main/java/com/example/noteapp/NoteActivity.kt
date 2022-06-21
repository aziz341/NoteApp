package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.noteapp.databinding.ActivityNoteBinding
import com.example.noteapp.models.NotesModel
import com.google.gson.Gson

class NoteActivity : AppCompatActivity() {

    private val binding: ActivityNoteBinding by lazy {
        ActivityNoteBinding.inflate(layoutInflater)
    }
    private var notesList = mutableListOf<NotesModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        binding.saveBtn.setOnClickListener {
            addNewNote()
        }

    }
    private fun addNewNote() {
        binding.apply {
            when {
                person1.text.isBlank() -> showToast()
                person2.text.isBlank() -> showToast()
                else -> {
                    val personItem = person1.text.toString()
                    val personItemText = person2.text.toString()
                    val newNote = NotesModel(
                        personItemText = personItemText,
                        personItem = personItem
                    )
                    saveData(newNote)
                }
            }
        }
    }
    private fun saveData(note: NotesModel) {
        notesList.add(note)
        val sharedPreferences = getSharedPreferences("saveNote", MODE_PRIVATE)
        val gson = Gson()
        val editor = sharedPreferences.edit()
        val saveObj: String = gson.toJson(notesList)
        editor.putString("MySaveData", saveObj)
        editor.apply()
        Toast.makeText(this, "Заметка успешно добавлена", Toast.LENGTH_SHORT).show()
        val intent = Intent(this,MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
    private fun showToast() {
        Toast.makeText(this, "cначала заполните все поля", Toast.LENGTH_SHORT).show()
    }


}