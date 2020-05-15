package com.example.shoppinglist.data

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.shoppinglist.logic.ShoppingNote

const val TABLE_NAME = "shopping_notes"

class NotesDB(val context: Context) : SQLiteOpenHelper(context, "shopping_notes", null, 1), NotesDAO {


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
                    "'Cable HDMI', " +
                    System.currentTimeMillis() + ")")
        )
        db.execSQL(("INSERT INTO $TABLE_NAME (title, content, date) VALUES (" +
                "'Compra viernes', " +
                "'Cereales, atún y lentejas', " +
                System.currentTimeMillis() + ")")
        )
        db.execSQL(("INSERT INTO $TABLE_NAME (title, content, date) VALUES (" +
                "'Compra papelería', " +
                "'Post-its y grapas', " +
                System.currentTimeMillis() + ")")
        )
        db.execSQL(("INSERT INTO $TABLE_NAME (title, content, date) VALUES (" +
                "'Compra para caseta', " +
                "'Alcohol', " +
                System.currentTimeMillis() + ")")
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { }

    override fun all(): List<ShoppingNote> {
        val list: MutableList<ShoppingNote> = ArrayList()

        val cursor = readableDatabase.rawQuery("SELECT * FROM $TABLE_NAME", null)
        while (cursor.moveToNext()) list.add( getShoppingNote(cursor) )
        cursor?.close()

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

    override fun add(note: ShoppingNote): Boolean {
        TODO("Not yet implemented")
    }

    override fun new(): Int {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int): Boolean {
        return writableDatabase.delete(TABLE_NAME, "_id = $id", null) > 0
    }

    override fun length(): Int {
        return getCursor().count
    }

    override fun update(id: Int, note: ShoppingNote): Boolean {
        TODO("Not yet implemented")
    }

    private fun getShoppingNote(cursor: Cursor) = ShoppingNote(
        id = cursor.getInt(0),
        title = cursor.getString(1),
        content = cursor.getString(2),
        date = cursor.getLong(3)
    )

    private fun getCursor(): Cursor = readableDatabase.rawQuery("SELECT * FROM $TABLE_NAME", null)

    /* TODO: Sustituir readableDatabase.rawQuery("SELECT * FROM $TABLE_NAME", null) por get Cursor ???? */
}