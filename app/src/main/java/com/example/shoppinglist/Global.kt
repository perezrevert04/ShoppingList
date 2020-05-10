package com.example.shoppinglist

import android.app.Application
import com.example.shoppinglist.presentation.MyAdapter

class Global : Application() {
    val viewAdapter by lazy {
        MyAdapter(
            fileList().toList()
        )
    }
}