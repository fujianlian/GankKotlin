package com.fujianlian.gankkotlin

import android.content.ContentValues
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fujianlian.gankkotlin.bean.GankBean
import com.fujianlian.gankkotlin.util.DatabaseOpenHelper
import com.fujianlian.gankkotlin.util.DatabaseOpenHelper.Columns
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.select

class WebViewModel : ViewModel() {

    val isCollect: MutableLiveData<Boolean> = MutableLiveData()
    val isDelete: MutableLiveData<Boolean> = MutableLiveData()
    val insertCollect: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var bean: GankBean

    fun checkCollect(dataBase: DatabaseOpenHelper) {
        dataBase.use {
            val a = select(DatabaseOpenHelper.TABLE)
                .whereSimple(Columns.id + " = ?", bean._id)
                .parseList(object : MapRowParser<Any> {
                    override fun parseRow(columns: Map<String, Any?>): Any {
                        return columns
                    }
                }).isNotEmpty()
            isCollect.postValue(a)
        }
    }

    fun addCollect(dataBase: DatabaseOpenHelper) {
        val values = ContentValues()
        values.put(Columns.id, bean._id)
        values.put(Columns.image, bean.image)
        values.put(Columns.type, bean.type)
        values.put(Columns.who, bean.who)
        values.put(Columns.desc, bean.desc)
        values.put(Columns.publishAt, bean.publishedAt)
        values.put(Columns.url, bean.url)
        dataBase.use {
            val a = insert(DatabaseOpenHelper.TABLE, null, values)
            insertCollect.postValue(a != -1L)
        }
    }

    fun deleteCollect(dataBase: DatabaseOpenHelper) {
        dataBase.use {
            val a = delete(DatabaseOpenHelper.TABLE, Columns.id + " = ?", arrayOf(bean._id))
            isDelete.postValue(a == 1)
        }
    }
}
