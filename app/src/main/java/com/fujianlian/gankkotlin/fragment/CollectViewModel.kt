package com.fujianlian.gankkotlin.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fujianlian.gankkotlin.bean.GankBean
import com.fujianlian.gankkotlin.util.DatabaseOpenHelper
import com.fujianlian.gankkotlin.util.DatabaseOpenHelper.Columns
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.select

class CollectViewModel : ViewModel() {

    val list: MutableLiveData<List<GankBean>> = MutableLiveData()

    fun getList(dataBase: DatabaseOpenHelper) {
        dataBase.use {
            list.value = select(DatabaseOpenHelper.TABLE)
                .parseList(object : MapRowParser<GankBean> {
                    override fun parseRow(columns: Map<String, Any?>): GankBean {
                        val bean = GankBean()
                        bean._id = columns[Columns.id].toString()
                        bean.desc = columns[Columns.title].toString()
                        bean.publishedAt = columns[Columns.time].toString()
                        bean.who = columns[Columns.who].toString()
                        bean.url = columns[Columns.url].toString()
                        bean.type = columns[Columns.type].toString()
                        bean.image  = columns[Columns.image].toString()
                        return bean
                    }
                })
        }
    }
}
