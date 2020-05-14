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
        Log.d("Database log", "Creando tabla...")
        db.execSQL(
            "CREATE TABLE $TABLE_NAME (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "title TEXT, " +
                    "content TEXT, " +
                    "date BIGINT " +
                    ")"
        )
        Log.d("Database log", "Tabla creada!")
        Log.d("Database log", "Insertando primer elemento...")
        db.execSQL(
            ("INSERT INTO $TABLE_NAME (title, content, date) VALUES (" +
                    "'Compra Amazon', " +
                    "'Cable HDMI', " +
                    System.currentTimeMillis() + ")")
        )
        Log.d("Database log", "Insertando segundo elemento...")
        db.execSQL(("INSERT INTO $TABLE_NAME (title, content, date) VALUES (" +
                "'Compra viernes', " +
                "'Cereales, atún y lentejas', " +
                System.currentTimeMillis() + ")")
        )
        Log.d("Database log", "Insertando tercer elemento...")
        db.execSQL(("INSERT INTO $TABLE_NAME (title, content, date) VALUES (" +
                "'Compra papelería', " +
                "'Post-its y grapas', " +
                System.currentTimeMillis() + ")")
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { }

    override fun element(id: Int): ShoppingNote {

        val cursor = readableDatabase.rawQuery("SELECT * FROM $TABLE_NAME WHERE _id = $id", null)
        Log.d("Database log", "cursor count: ${cursor.count}")
        Log.d("Database log", "cursor == null? ${cursor == null}")
//        Log.d("Database log", "Cursor move to first: ${cursor.moveToFirst()}")

        try {

        Log.d("Database log", "Cursor move to next: ${cursor.moveToNext()}")
            if (cursor.moveToNext()){
                Log.d("Database log", "Entrando al if cursor.moveToNext()")
                return getShoppingNote(cursor)}
            else
                return ShoppingNote("Title $id", "Content")
//                throw SQLException("Error when accessing the item _id = $id")

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
        TODO("Not yet implemented")
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