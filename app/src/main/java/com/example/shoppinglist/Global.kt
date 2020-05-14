package com.example.shoppinglist

import android.app.Application
import com.example.shoppinglist.data.NotesDB
import com.example.shoppinglist.presentation.ShoppingNotesAdapter

class Global() : Application() {

    private val shoppingNotes = NotesDB(this)
    val adapter by lazy { ShoppingNotesAdapter(shoppingNotes, shoppingNotes.getCursor()) }

}