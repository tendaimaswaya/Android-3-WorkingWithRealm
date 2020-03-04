package com.tutorial.workingwithrealm.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

open class BaseActivity:AppCompatActivity() {
    fun <T> alert(msg:T, length:Int = Toast.LENGTH_LONG){
        Toast.makeText(this,msg.toString(),length).show()
    }
    fun RecyclerView.initRecyclerview(defaultOrientation:Int = RecyclerView.VERTICAL): RecyclerView {
        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.orientation = defaultOrientation
        layoutManager = mLayoutManager
        itemAnimator = DefaultItemAnimator()
        return this
    }

}