package com.example.shoppinglist.logic

class ShoppingNote (
    val id: Int? = null,
    var title: String = "",
    var content: String = "",
    var date: Long = System.currentTimeMillis()
)