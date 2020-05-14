package com.example.shoppinglist.data

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.shoppinglist.logic.ShoppingNote

class NotesDB(val context: Context) : SQLiteOpenHelper(context, "shopping_notes", null, 1), NotesDAO {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE shopping_notes (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "content TEXT, " +
                "date BIGINT, " +
                ")")
        db.execSQL(("INSERT INTO shopping_notes (null, " +
                "'Compra Amazon' ," +
                "'Cable HDMI'" +
                System.currentTimeMillis() + ", 3.0)"))
        db.execSQL(("INSERT INTO shopping_notes (null, " +
                "'Compra viernes' ," +
                "'Cereales, atún y lentejas'" +
                System.currentTimeMillis() + ", 5.0)"))
        db.execSQL(("INSERT INTO shopping_notes (null, " +
                "'Compra papelería' ," +
                "'Post-its y grapas'" +
                System.currentTimeMillis() + ", 2.0)"))
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { }

    override fun element(id: Int): ShoppingNote {
        TODO("Not yet implemented")
    }

    override fun add(note: ShoppingNote): Boolean {
        TODO("Not yet implemented")
    }

    override fun new(): Int {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun length(): Int {
        TODO("Not yet implemented")
    }

    override fun update(id: Int, note: ShoppingNote): Boolean {
        TODO("Not yet implemented")
    }

    fun getShoppingNote(cursor: Cursor) = ShoppingNote(
        title = cursor.getString(1),
        content = cursor.getString(2),
        date = cursor.getLong(3)
    )

    fun getCursor(): Cursor = readableDatabase.rawQuery("SELECT * FROM shopping_notes", null)
}