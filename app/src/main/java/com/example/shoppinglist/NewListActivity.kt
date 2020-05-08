package com.example.shoppinglist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_new_list.*
import org.jetbrains.anko.toast
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception

class NewListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_list)

        this.cargarFichero()
    }

    fun onCancel(view: View) {
        onBackPressed()
    }

    fun onSave(view: View) {
        this.crearFichero()
        onBackPressed()
//        toast("Guardado (pendiente de traducción)")
    }

    fun crearFichero() {
        var fos: FileOutputStream

        try {
            fos = openFileOutput("fichero.txt", Context.MODE_PRIVATE)
            fos.write(shoppingList.text.toString().toByteArray())
            fos.close()
            toast("Fichero creado correctamente")
        } catch (e: FileNotFoundException) {
            toast("File not found exception")
        } catch (e: IOException) {
            toast("IO exception")
        }
    }

    fun cargarFichero() {

        var fis: FileInputStream
        try {
            fis = openFileInput("fichero.txt")
            fis.bufferedReader().use { shoppingList.setText(it.readText()) }
            fis.close()
            val files = fileList()
            files.forEach {
                toast(it.toString())
            }
        } catch (e: Exception) {
            toast("Se ha producido un error")
        }
    }
}
