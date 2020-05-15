package com.example.shoppinglist.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.Global
import com.example.shoppinglist.R
import com.example.shoppinglist.data.NotesDB
import com.example.shoppinglist.use_case.NotesUseCase
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    private val shoppingNotes: NotesDB by lazy { (application as Global).shoppingNotes }
    private val viewAdapter by lazy { (application as Global).adapter }
    private val notesUseCase by lazy { NotesUseCase(this) }
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { _ -> startActivity( Intent(this, NewListActivity::class.java) ) }
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

    private fun onAbout() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    private fun onExit() {
        toast(R.string.come_back)
        finish()
    }

    private fun prepareRecyclerView() {
        viewAdapter.notes = shoppingNotes.all()

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = viewAdapter
        }

        viewAdapter.onClick = {
            notesUseCase.showNote(it.tag as Int)
        }

        viewAdapter.onLongClick = {
            showAlert(it.tag as Int)
        }

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

    }

    private fun showAlert(id: Int) {
        val note = shoppingNotes.element(id)

        val builder = AlertDialog.Builder(this)
        builder.setTitle( note.title ).setMessage( note.content )

        builder.setNeutralButton(R.string.close) { _, _ -> }

        builder.setNegativeButton(R.string.delete){ _, _ ->
            val result = shoppingNotes.delete(id)
            if (result) prepareRecyclerView()
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

}
