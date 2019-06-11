package com.fujianlian.gankkotlin.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fujianlian.gankkotlin.bean.CollectBean
import com.fujianlian.gankkotlin.util.DatabaseOpenHelper
import com.fujianlian.gankkotlin.util.DatabaseOpenHelper.Columns
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.select

class CollectViewModel : ViewModel() {

    val list: MutableLiveData<List<CollectBean>> = MutableLiveData()

    fun getList(dataBase: DatabaseOpenHelper) {
        dataBase.use {
            list.value = select(DatabaseOpenHelper.TABLE)
                .parseList(object : MapRowParser<CollectBean> {
                    override fun parseRow(columns: Map<String, Any?>): CollectBean {
                        val bean = CollectBean()
                        bean.id = columns[Columns.id].toString()
                        bean.title = columns[Columns.title].toString()
                        bean.time = columns[Columns.time].toString()
                        bean.who = columns[Columns.who].toString()
                        bean.url = columns[Columns.url].toString()
                        bean.type = columns[Columns.type].toString()
                        bean.image = columns[Columns.image].toString()
                        return bean
                    }
                })
        }
    }
}
