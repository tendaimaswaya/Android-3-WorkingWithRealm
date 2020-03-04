package com.tutorial.workingwithrealm.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tutorial.workingwithrealm.model.TodoItem
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.coroutines.awaitAll
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class TodoViewModel : ViewModel() {
    val realm = Realm.getDefaultInstance()
    var list = ArrayList<TodoItem>()

    private val todos: MutableLiveData<List<TodoItem>> by lazy {
        MutableLiveData<List<TodoItem>>().also {
            it.value =  loadTodoList()
        }
    }

    fun getTodos(): LiveData<List<TodoItem>> {
        return todos
    }

    private fun loadTodoList():ArrayList<TodoItem> {
       list.clear()
       realm.executeTransaction { realm ->
          realm.where(TodoItem::class.java).findAll().toCollection(list).asReversed()
       }
        return list
    }

     fun addToDoItem(title:String){
        list.clear() //clear list in order to populate with new details
        realm.executeTransaction { realm->
           realm.createObject<TodoItem>(UUID.randomUUID().toString()).title = title //create the item and store in realm with unique id
        }
        todos.value = loadTodoList()
    }

    fun deleteToDoItem(id:String){
        list.clear() //clear list in order to populate with new details
        realm.executeTransaction {
            realm -> realm.where<TodoItem>().equalTo("id",id).findAll()!!.deleteFirstFromRealm()
        }
        todos.value = loadTodoList()
    }
}