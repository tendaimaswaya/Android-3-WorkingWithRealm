package com.tutorial.workingwithrealm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tutorial.workingwithrealm.ClickListener
import com.tutorial.workingwithrealm.R
import com.tutorial.workingwithrealm.model.TodoItem

internal class TodoAdapter (var listener: ClickListener) : RecyclerView.Adapter<TodoAdapter.NewsViewHolder>() {

    private var mDataSource: List<TodoItem>? = null

    fun setDataSource(dataSource: List<TodoItem>) {
        this.mDataSource = dataSource
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_to_do, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val dataItem = mDataSource!![position]
        holder.bind(dataItem, listener)
    }

    override fun getItemCount(): Int {
        return mDataSource?.size ?: 0
    }

    internal class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var todoItem: TodoItem? = null

        private val title : TextView? by lazy {
            itemView.findViewById(R.id.title) as TextView
        }
        private val delete : ImageView? by lazy {
            itemView.findViewById(R.id.delete) as ImageView
        }

        private val container: RelativeLayout? by lazy {
            itemView.findViewById(R.id.container) as RelativeLayout
        }

        fun bind(todoItem: TodoItem, listener: ClickListener) {
            this.todoItem = todoItem
            title!!.text = todoItem.title

            container!!.setOnClickListener { _ ->
                listener.onClick(todoItem) // .onClick(transaction)
            }
            delete!!.setOnClickListener {
                listener.onDelete(todoItem)
            }
        }
    }

}