package com.fujianlian.gankkotlin.util

class HttpModel<T> {
    var isError: Boolean = false
    var results: T? = null

    override fun toString(): String {
        return "BaseModel{" +
                "error=" + isError +
                ", results=" + results +
                '}'.toString()
    }
}