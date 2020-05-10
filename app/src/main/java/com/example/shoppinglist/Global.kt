package com.example.shoppinglist

import android.app.Application

class Global : Application() {
    val viewAdapter by lazy { MyAdapter( fileList().toList() ) }
}