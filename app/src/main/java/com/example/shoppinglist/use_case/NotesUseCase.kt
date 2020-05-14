package com.example.shoppinglist.use_case

import android.app.Activity
import android.content.Intent
import com.example.shoppinglist.data.NotesDB
import com.example.shoppinglist.presentation.AboutActivity
import com.example.shoppinglist.presentation.EditListActivity
import com.example.shoppinglist.presentation.ShoppingNotesAdapter

class NotesUseCase(
    private val activity: Activity,
    private val notes: NotesDB,
    private val adapter: ShoppingNotesAdapter
) {

    fun showNote(position: Int) {
        val intent = Intent(activity, EditListActivity::class.java)
        intent.putExtra("position", position)
        activity.startActivity(intent)
    }
}