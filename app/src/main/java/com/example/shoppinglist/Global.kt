package com.example.shoppinglist

import android.app.Application
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.presentation.MyAdapter
import org.jetbrains.anko.toast

class Global(
    var viewAdapter: MyAdapter = MyAdapter( arrayOf("").toList() )
) : Application() {

//    val viewAdapter by lazy {
//        MyAdapter( fileList().toList() )
//    }

    override fun onCreate() {
        super.onCreate()
        viewAdapter = MyAdapter( fileList().toList() )
    }

    fun update() {
        viewAdapter = MyAdapter( fileList().toList() )
    }

}