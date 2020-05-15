package com.example.shoppinglist.logic

class ShoppingNote (
    val id: Int,
    var title: String = "",
    var content: String = "",
    var date: Long = System.currentTimeMillis()
)