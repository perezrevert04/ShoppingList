package com.example.shoppinglist.presentation

import android.database.Cursor
import com.example.shoppinglist.data.NotesDB
import com.example.shoppinglist.logic.ShoppingNote

class ShoppingNotesAdapter(private val shoppingNotes: NotesDB, var cursor: Cursor) : MyAdapter(shoppingNotes) {

    fun notePosition(position: Int): ShoppingNote {
        cursor.moveToPosition(position)
        return shoppingNotes.getShoppingNote(cursor)
    }

    fun idPosition(position: Int): Int {
        cursor.moveToPosition(position)
        return if (cursor.count > 0) cursor.getInt(0) else -1
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.textView.tag = position
    }

    override fun getItemCount(): Int = cursor.count
}