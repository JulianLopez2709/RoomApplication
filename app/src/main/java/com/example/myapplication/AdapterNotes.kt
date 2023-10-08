package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemNotesBinding

class AdapterNotes(private var notes: List<Notes>) :
    RecyclerView.Adapter<AdapterNotes.ViewHolder>() {

    var onItemClick: ((Notes) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = notes[position]
        holder.bind(item,onItemClick)
    }

    class ViewHolder(private val binding: ItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Notes, onItemClick: ((Notes) -> Unit)?) {
            binding.tvNoteTitle.text = item.noteTitle
            binding.tvNoteDescription.text = item.noteDescription

            binding.root.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }

    fun setData(newList: List<Notes>) {
        notes = newList
        notifyDataSetChanged()
    }
}