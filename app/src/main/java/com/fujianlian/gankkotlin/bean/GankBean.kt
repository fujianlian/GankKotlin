package com.fujianlian.gankkotlin.bean

import com.stx.xhb.androidx.entity.SimpleBannerInfo

class GankBean: SimpleBannerInfo() {

    var desc = ""
    var who = ""
    var url = ""
    var images: List<String>? = null

    override fun getXBannerUrl(): String {
        return url
    }

    override fun getXBannerTitle(): String {
        return desc
    }

    override fun toString(): String {
        return "desc=$desc,who=$who,url=$url , images=${images?.toString()}"
    }
}