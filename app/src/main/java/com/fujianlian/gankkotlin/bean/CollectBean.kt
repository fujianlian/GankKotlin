package com.fujianlian.gankkotlin.bean

import android.view.View
import com.fujianlian.gankkotlin.WebActivity
import org.jetbrains.anko.startActivity

class CollectBean {

    var id = ""
    var image = ""
    var type = ""
    var who = ""
    var title = ""
    var time = ""
    var url = ""

    fun goWeb(view: View) {
        view.context.startActivity<WebActivity>("title" to title, "url" to url)
    }
}