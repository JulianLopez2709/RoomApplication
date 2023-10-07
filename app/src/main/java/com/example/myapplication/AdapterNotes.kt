package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemNotesBinding

class AdapterNotes(private var notes:List<Notes>):RecyclerView.Adapter<AdapterNotes.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item = notes[position]
       holder.bind(item)
    }

    class ViewHolder(private val binding:ItemNotesBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Notes) {
            binding.tvNoteTitle.text = item.noteTitle
            binding.tvNoteDescription.text = item.noteDescription
        }
    }

    fun setData(newList:List<Notes>){
        notes = newList
        notifyDataSetChanged()
    }
}