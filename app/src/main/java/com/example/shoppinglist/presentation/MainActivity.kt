package com.example.shoppinglist.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.Global
import com.example.shoppinglist.R
import com.example.shoppinglist.use_case.ShowListUseCase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.element_list.view.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {


    val viewAdapter by lazy { (application as Global).viewAdapter }
    val showListUseCase by lazy { ShowListUseCase(this) }
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        this.prepareRecyclerView()

        fab.setOnClickListener { view -> startActivity( Intent(this, NewListActivity::class.java) ) }
    }

    override fun onResume() {
        super.onResume()
        this.prepareRecyclerView()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                onAbout()
                true
            }
            R.id.action_exit -> {
                onExit()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun onAbout() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    fun onExit() {
        toast(R.string.come_back)
        finish()
    }

    private fun prepareRecyclerView() {
        val files = fileList()
        files.sort()

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = viewAdapter
        }

        viewAdapter.onClick = {
            showListUseCase.showList(it.text_view.text.toString())
        }

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

    }

}
