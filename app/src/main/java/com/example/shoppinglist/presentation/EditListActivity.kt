package com.example.shoppinglist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.Global
import com.example.shoppinglist.R
import com.example.shoppinglist.data.NotesDB
import com.example.shoppinglist.logic.ShoppingNote
import kotlinx.android.synthetic.main.activity_new_list.*
import org.jetbrains.anko.toast

class EditListActivity : AppCompatActivity() {

    private val notes: NotesDB by lazy { (application as Global).shoppingNotes }
    private lateinit var note: ShoppingNote

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_list)

        getShoppingNote()

        buttonCancel.setOnClickListener { onBackPressed() }
        buttonSave.setOnClickListener {
            if (saveFile()) onBackPressed()
            else toast(R.string.error)
        }
    }

    private fun getShoppingNote() {
        val id = intent.getIntExtra("id", -1)
        note = notes.element(id)

        listName.setText(note.title)
        shoppingList.setText(note.content)
    }

    private fun saveFile(): Boolean {
        note.title = listName.text.toString()
        note.content = shoppingList.text.toString()

        return notes.update(note.id, note)
    }
}
