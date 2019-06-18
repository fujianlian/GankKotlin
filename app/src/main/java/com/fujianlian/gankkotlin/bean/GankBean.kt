package com.fujianlian.gankkotlin.bean

import android.os.Parcelable
import android.view.View
import com.fujianlian.gankkotlin.WebActivity
import com.stx.xhb.androidx.entity.SimpleBannerInfo
import kotlinx.android.parcel.Parcelize
import org.jetbrains.anko.startActivity

@Parcelize
class GankBean(
    var _id: String,
    var publishedAt: String,
    var type: String,
    var desc: String,
    var who: String,
    var url: String,
    var image: String,
    var isCollect: Boolean = false,
    var images: ArrayList<String>? = null
) : SimpleBannerInfo(),
    Parcelable {

    override fun getXBannerUrl(): String {
        return url
    }

    override fun getXBannerTitle(): String {
        return desc
    }

    fun goWeb(view: View) {
        view.context.startActivity<WebActivity>("bean" to this, "fromList" to true)
    }

    fun getWhos(): String {
        return "$who · $type"
    }
}