package com.tutorial.workingwithrealm.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class TodoItem(@PrimaryKey var id:String, var title:String?):RealmObject(){constructor():this("",null)}