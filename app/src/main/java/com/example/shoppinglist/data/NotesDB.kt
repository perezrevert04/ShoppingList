package com.example.shoppinglist.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.shoppinglist.logic.ShoppingNote

const val TABLE_NAME = "shopping_notes"

class NotesDB(context: Context) : SQLiteOpenHelper(context, "shopping_notes", null, 1), NotesDAO {


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE $TABLE_NAME (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "title TEXT, " +
                    "content TEXT, " +
                    "date BIGINT " +
                    ")"
        )
        db.execSQL(
            ("INSERT INTO $TABLE_NAME (title, content, date) VALUES (" +
                    "'Compra Amazon', " +
                    "'Cable HDMI\nFunda para el móvil', " +
                    System.currentTimeMillis() + ")")
        )
        db.execSQL(("INSERT INTO $TABLE_NAME (title, content, date) VALUES (" +
                "'Compra de la semana', " +
                "'Patatas\nHuevos\nAceitunas\nManzanas', " +
                System.currentTimeMillis() + ")")
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { }

    override fun all(): List<ShoppingNote> {
        val list: MutableList<ShoppingNote> = ArrayList()

        val cursor = getCursor()
        while (cursor.moveToNext()) list.add( getShoppingNote(cursor) )
        cursor.close()

        return list
    }

    override fun element(id: Int): ShoppingNote {

        val cursor = readableDatabase.rawQuery("SELECT * FROM $TABLE_NAME WHERE _id = $id", null)

        try {
            if (cursor.moveToNext()) return getShoppingNote(cursor)
            else throw SQLException("Error accessing the element $id")
        } catch (e: Exception) {
            throw e
        } finally {
            cursor?.close()
        }
    }

    override fun create(note: ShoppingNote): Boolean {
        val values = ContentValues().apply {
            put("title", note.title)
            put("content", note.content)
            put("date", System.currentTimeMillis())
        }

        return writableDatabase.insert(TABLE_NAME, null, values) > -1
    }

    override fun delete(id: Int): Boolean {
        return writableDatabase.delete(TABLE_NAME, "_id = $id", null) > 0
    }

    override fun length(): Int {
        return getCursor().count
    }

    override fun update(id: Int, note: ShoppingNote): Boolean {
        val values = ContentValues().apply {
            put("title", note.title)
            put("content", note.content)
            put("date", System.currentTimeMillis())
        }

        return writableDatabase.update(TABLE_NAME, values, "_id = $id", null) > 0
    }

    private fun getShoppingNote(cursor: Cursor) = ShoppingNote(
        id = cursor.getInt(0),
        title = cursor.getString(1),
        content = cursor.getString(2),
        date = cursor.getLong(3)
    )

    private fun getCursor(): Cursor = readableDatabase.rawQuery("SELECT * FROM $TABLE_NAME", null)

}