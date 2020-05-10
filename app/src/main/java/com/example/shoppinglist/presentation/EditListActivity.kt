package com.example.shoppinglist.presentation

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.R
import kotlinx.android.synthetic.main.activity_new_list.*
import org.jetbrains.anko.toast
import java.io.FileInputStream
import java.io.FileOutputStream

class EditListActivity : AppCompatActivity() {

    private lateinit var filename: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_list)

        getFile()
        initializeForm()

        buttonCancel.setOnClickListener { onBackPressed() }
        buttonSave.setOnClickListener {
            saveFile()
            onBackPressed()
        }
    }

    private fun getFile() {
        filename = intent.getStringExtra("name") ?: "list"

        var fis: FileInputStream
        try {
            fis = openFileInput("fichero.txt")
            fis.bufferedReader().use { shoppingList.setText(it.readText()) }
            fis.close()

        } catch (e: Exception) {
            toast("Se ha producido un error")
            onBackPressed()
        }
    }

    private fun initializeForm() {
        listName.setText( filename.dropLast(4) )
    }

    private fun saveFile() {
        var fos: FileOutputStream

        try {
            val newName = listName.text.toString() + ".txt"

            fos = openFileOutput(newName, Context.MODE_PRIVATE)
            fos.write(shoppingList.text.toString().toByteArray())
            fos.close()

            if (newName != filename) deleteFile(filename)
        } catch (e: Exception) {
            toast("Se ha producido un error")
        }
    }
}
