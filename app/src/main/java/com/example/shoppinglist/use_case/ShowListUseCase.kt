package com.example.shoppinglist.use_case

import android.app.Activity
import android.content.Intent
import com.example.shoppinglist.presentation.AboutActivity
import com.example.shoppinglist.presentation.EditListActivity

class ShowListUseCase(private val activity: Activity) {

    fun showList(listName: String) {
        val intent = Intent(activity, EditListActivity::class.java)
        intent.putExtra("name", listName)
        activity.startActivity(intent)
    }
}