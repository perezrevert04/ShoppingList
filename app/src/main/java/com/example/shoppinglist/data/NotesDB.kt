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

    override fun element(id: Int): ShoppingNote {

        val cursor = readableDatabase.rawQuery("SELECT * FROM $TABLE_NAME WHERE _id = ${id + 1}", null)
        val c = readableDatabase.rawQuery("SELECT * FROM $TABLE_NAME", null)

        c.moveToPosition(2)
        Log.d("Database", c.getString(1))
        c.close()
        try {
            if (cursor.moveToNext())
                return getShoppingNote(cursor)
            else
                throw SQLException("Error accessing the element $id")

        } catch (e:Exception) {
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
        TODO("Not yet implemented")
    }

    override fun length(): Int {
        return getCursor().count
    }

    override fun update(id: Int, note: ShoppingNote): Boolean {
        TODO("Not yet implemented")
    }

    private fun getShoppingNote(cursor: Cursor) = ShoppingNote(
        title = cursor.getString(1),
        content = cursor.getString(2),
        date = cursor.getLong(3)
    )

    fun getCursor(): Cursor = readableDatabase.rawQuery("SELECT * FROM $TABLE_NAME", null)
}