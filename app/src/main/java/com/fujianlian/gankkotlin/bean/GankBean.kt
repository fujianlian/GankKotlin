package com.fujianlian.gankkotlin.bean

import android.view.View
import com.fujianlian.gankkotlin.WebActivity
import com.stx.xhb.androidx.entity.SimpleBannerInfo
import org.jetbrains.anko.startActivity

class GankBean : SimpleBannerInfo() {

    var _id = ""
    var publishedAt = ""
    var type = ""
    var desc = ""
    var who = ""
    var url = ""
    var images: List<String>? = null
    var image: String? = null

    override fun getXBannerUrl(): String {
        return url
    }

    override fun getXBannerTitle(): String {
        return desc
    }

    override fun toString(): String {
        return "desc=$desc,who=$who,url=$url , images=${images?.toString()}"
    }


    fun goWeb(view: View) {
        view.context.startActivity<WebActivity>("title" to desc, "url" to url)
    }

    fun getWhos(): String {
        return "$who · $type"
    }

}