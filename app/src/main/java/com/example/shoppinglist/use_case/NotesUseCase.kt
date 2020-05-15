package com.example.shoppinglist.use_case

import android.app.Activity
import android.content.Intent
import com.example.shoppinglist.presentation.EditListActivity

class NotesUseCase(
    private val activity: Activity/*,
    private val notes: NotesDB,
    private val adapter: ShoppingNotesAdapter*/
) {

    fun showNote(id: Int) {
        val intent = Intent(activity, EditListActivity::class.java)
        intent.putExtra("id", id)
        activity.startActivity(intent)
    }
}