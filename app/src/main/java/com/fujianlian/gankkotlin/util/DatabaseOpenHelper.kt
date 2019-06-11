package com.fujianlian.gankkotlin.util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseOpenHelper private constructor(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "MyDatabase", null, 1) {
    init {
        instance = this
    }

    companion object {
        private var instance: DatabaseOpenHelper? = null

        const val TABLE = "Collect" // 所有交易对信息

        @Synchronized
        fun getInstance(ctx: Context) = instance ?: DatabaseOpenHelper(ctx.applicationContext)
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            TABLE, true,
            Columns.id to TEXT + PRIMARY_KEY + UNIQUE,
            Columns.image to TEXT,
            Columns.type to TEXT,
            Columns.who to TEXT,
            Columns.title to TEXT,
            Columns.time to TEXT,
            Columns.url to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(TABLE, true)
    }

    object Columns {
        const val id = "id"
        const val image = "image"
        const val type = "type"
        const val who = "who"
        const val title = "title"
        const val time = "time"
        const val url = "url"
    }
}

// Access property for Context
val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(this)