package com.example.shoppinglist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.Global
import com.example.shoppinglist.R
import com.example.shoppinglist.data.NotesDB
import com.example.shoppinglist.logic.ShoppingNote
import kotlinx.android.synthetic.main.activity_new_list.*
import org.jetbrains.anko.toast

class NewListActivity : AppCompatActivity() {

    private val notes: NotesDB by lazy { (application as Global).shoppingNotes }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_list)

        buttonCancel.setOnClickListener { onBackPressed() }
        buttonSave.setOnClickListener {
            if (saveFile()) onBackPressed()
            else toast(R.string.error)
        }
    }

    private fun saveFile(): Boolean {
        val note = ShoppingNote(title = listName.text.toString(), content = shoppingList.text.toString())
        return notes.create(note)
    }
}
