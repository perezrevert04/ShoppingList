package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.NotesDAO
import kotlinx.android.synthetic.main.element_list.view.*

open class MyAdapter(private val notes: NotesDAO) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    lateinit var onClick: (View) -> Unit
    lateinit var onLongClick: (View) -> Unit

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val textView: ConstraintLayout) : RecyclerView.ViewHolder(textView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.element_list, parent, false) as ConstraintLayout
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.text_view.text = notes.element(position).title
        holder.textView.tag = position.toString()
        holder.textView.setOnClickListener { onClick(holder.itemView) }
        holder.textView.setOnLongClickListener { onLongClick(holder.itemView); true }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = notes.length()
}