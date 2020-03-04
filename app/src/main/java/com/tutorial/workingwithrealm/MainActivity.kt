package com.tutorial.workingwithrealm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.tutorial.workingwithrealm.adapter.TodoAdapter
import com.tutorial.workingwithrealm.base.BaseActivity
import com.tutorial.workingwithrealm.model.TodoItem
import com.tutorial.workingwithrealm.viewmodel.TodoViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import io.realm.Realm
import io.realm.kotlin.createObject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), ClickListener {
     private lateinit var adapter:TodoAdapter
     val model: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = TodoAdapter(this)
        recyclerView.initRecyclerview().adapter = adapter

        model.getTodos().observe(this, Observer<List<TodoItem>>{ todos ->
            adapter.setDataSource(todos)
        })
    }

    fun addTodo(view: View) = run{
        model.addToDoItem(title_et.text.toString())
    }

    override fun <T> onClick(data: T) {
        alert((data as TodoItem).title)
    }

    override fun <T> onDelete(data: T) {
        model.deleteToDoItem((data as TodoItem).id)
    }
}
