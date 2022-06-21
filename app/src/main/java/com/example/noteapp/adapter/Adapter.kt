package com.example.noteapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.databinding.ItemBinding
import com.example.noteapp.models.NotesModel

class Adapter(private val actionListener:ItemOnClickListenner): RecyclerView.Adapter<Adapter.ViewHolder>() {
    var notesList: MutableList<NotesModel> = mutableListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NotesModel) {
            binding.apply {
                with(binding) {
                personItemText.text = note.personItemText
                    personItem.text = note.personItem

                    itemView.setOnClickListener {

                        actionListener.showCustomAlertDialog(position = adapterPosition,car = note)
                    }

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        val binding = ItemBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notesList[position])
    }

    fun updateCar(position: Int, newPerson: NotesModel, newName: NotesModel) {
        notesList[position].personItem = newPerson.personItem
        notesList[position].personItem = newName.personItemText
        notifyItemChanged(position)

    }
    fun deleteCar(position: Int) {
        notesList.removeAt(position)
        notifyItemRemoved(position)
    }




    override fun getItemCount(): Int = notesList.size
    }
interface ItemOnClickListenner {
    fun showCustomAlertDialog(position: Int, car: NotesModel)
}

