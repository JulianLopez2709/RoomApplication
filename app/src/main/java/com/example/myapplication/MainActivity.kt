package com.example.myapplication

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ViewModel

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AdapterNotes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        binding.fabAddNewNote.setOnClickListener {
            showAddNoteDialog()
        }

        viewModel.allNotes.observe(this) {
            println("Here: ${it}")
            adapter.setData(it)
        }

    }

    private fun initAdapter() {
        adapter = AdapterNotes(emptyList())
        binding.recyclerViewMain.adapter = adapter
        binding.recyclerViewMain.layoutManager = LinearLayoutManager(this)

        adapter.onItemClick = { showActionDialog(it) }
    }

    private fun showActionDialog(it: Notes) {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Select Action")

        builder.setPositiveButton("Delete") { _, _ ->
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Update") { _, _ ->
            showUpdateDialog(it)
        }
        builder.setNeutralButton("Cancel") { _, _ ->
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

    private fun showUpdateDialog(it: Notes) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_update_note)
        dialog.setCancelable(true)

        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window!!.attributes)
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT

        val etNoteTitle = dialog.findViewById<EditText>(R.id.etNoteTitle)
        val etNoteDescription = dialog.findViewById<EditText>(R.id.etNoteDescription)

        etNoteTitle.setText(it.noteTitle)
        etNoteDescription.setText(it.noteDescription)

        dialog.findViewById<Button>(R.id.btnCancel).setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<Button>(R.id.btnUpdateNote).setOnClickListener {
            if (inputCheck(etNoteTitle.text.toString(), etNoteDescription.text.toString())) {
                val notes = Notes(
                    noteTitle = etNoteTitle.text.toString(),
                    noteDescription = etNoteDescription.text.toString(),
                    id = it.id
                )
                viewModel.updateNote(notes)
                Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Please enter data", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
        dialog.window!!.attributes = layoutParams
    }

    private fun showAddNoteDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_add_new_note)
        dialog.setCancelable(true)

        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window!!.attributes)
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT

        val etNoteTitle = dialog.findViewById<EditText>(R.id.etNoteTitle)
        val etNoteDescription = dialog.findViewById<EditText>(R.id.etNoteDescription)

        dialog.findViewById<Button>(R.id.btnCancel).setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<Button>(R.id.btnAddNote).setOnClickListener {
            if (inputCheck(etNoteTitle.text.toString(), etNoteDescription.text.toString())) {
                val notes = Notes(
                    noteTitle = etNoteTitle.text.toString(),
                    noteDescription = etNoteDescription.text.toString(),
                    id = 0
                )
                viewModel.addData(notes)
                Toast.makeText(this, "Data added", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Please enter data", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
        dialog.window!!.attributes = layoutParams
    }

    private fun inputCheck(noteTitle: String, noteDescription: String): Boolean {
        return !(TextUtils.isEmpty(noteTitle) && TextUtils.isEmpty(noteDescription))
    }
}