package com.hurtarte.demobowpi.fragments.data

class Task2 {


    constructor(){

    }

    constructor(id: String?, name: String?, description: String?, date: String?, done: Boolean?) {
        this.id = id
        this.name = name
        this.description = description
        this.date = date
        this.done = done
    }

    var id:String?=null
    var name:String?=null
    var description:String?=null
    var date:String?=null
    var done:Boolean?=null
}