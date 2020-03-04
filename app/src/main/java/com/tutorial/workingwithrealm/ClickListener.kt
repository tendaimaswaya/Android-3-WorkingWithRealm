package com.tutorial.workingwithrealm

interface ClickListener {
    fun <T> onClick(data:T)
    fun <T> onDelete(data:T)
}