package com.example.shoppinglist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {


    val viewAdapter by lazy { (application as Global).viewAdapter }

    private lateinit var recyclerView: RecyclerView
//    private lateinit var viewAdapter: RecyclerView.Adapter<*>

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

//        viewAdapter = MyAdapter( files.map { it.dropLast(4) } )

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = LinearLayoutManager(this@MainActivity)
            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }

        viewAdapter.onClick = {
            val pos = recyclerView.getChildAdapterPosition(it)
            toast(pos)
        }

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)


    }

}
