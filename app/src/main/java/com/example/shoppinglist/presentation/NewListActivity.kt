package com.example.shoppinglist.presentation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.shoppinglist.R
import kotlinx.android.synthetic.main.activity_new_list.*
import org.jetbrains.anko.toast
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class NewListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_list)
    }

    fun onCancel(view: View) {
        onBackPressed()
    }

    fun onSave(view: View) {
        this.saveFile()
        onBackPressed()
    }

    private fun saveFile() {
        var fos: FileOutputStream

        try {
            fos = openFileOutput(listName.text.toString() + ".txt", Context.MODE_PRIVATE)
            fos.write(shoppingList.text.toString().toByteArray())
            fos.close()
        } catch (e: FileNotFoundException) {
            toast("File not found exception")
        } catch (e: IOException) {
            toast("IO exception")
        }
    }
}
