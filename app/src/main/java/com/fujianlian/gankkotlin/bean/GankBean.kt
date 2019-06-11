package com.fujianlian.gankkotlin.bean

class GankBean {
    var desc = ""
    var who = ""
    var url = ""
    var images: List<String>? = null

    override fun toString(): String {
        return "desc=$desc,who=$who,url=$url , images=${images?.toString()}"
    }
}