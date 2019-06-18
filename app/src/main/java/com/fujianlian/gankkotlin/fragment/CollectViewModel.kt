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
                        return GankBean(
                            columns[Columns.id].toString(),
                            columns[Columns.publishAt].toString(),
                            columns[Columns.type].toString(),
                            columns[Columns.desc].toString(),
                            columns[Columns.who].toString(),
                            columns[Columns.url].toString(),
                            columns[Columns.image].toString(),
                            true
                        )
                    }
                })
        }
    }
}
