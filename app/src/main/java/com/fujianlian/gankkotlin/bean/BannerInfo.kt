package com.fujianlian.gankkotlin.bean

import com.stx.xhb.androidx.entity.SimpleBannerInfo

class BannerInfo(private val title: String,private val url: String) : SimpleBannerInfo() {

    override fun getXBannerUrl(): String {
        return url
    }

    override fun getXBannerTitle(): String {
        return title
    }
}