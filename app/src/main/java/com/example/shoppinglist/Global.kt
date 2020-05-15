package com.example.shoppinglist

import android.app.Application
import com.example.shoppinglist.data.NotesDB
import com.example.shoppinglist.presentation.MyAdapter

class Global : Application() {

    val shoppingNotes = NotesDB(this)
    val adapter by lazy { MyAdapter(shoppingNotes.all()) }

}