package com.fujianlian.gankkotlin.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fujianlian.gankkotlin.bean.GankBean
import com.fujianlian.gankkotlin.util.HttpModel
import com.fujianlian.gankkotlin.util.ToastUtil
import com.vise.xsnow.http.ViseHttp
import com.vise.xsnow.http.callback.ACallback
import java.util.*

class HomeViewModel : ViewModel() {

    val bannerList: MutableLiveData<List<GankBean>> = MutableLiveData()
    val list: MutableLiveData<List<GankBean>> = MutableLiveData()

    fun getBannerList() {
        ViseHttp.GET("api/data/福利/5/1")
            .setHttpCache(true)
            .request(object : ACallback<HttpModel<List<GankBean>>>() {
                override fun onSuccess(data: HttpModel<List<GankBean>>) {
                    bannerList.postValue(data.results!!)
                }

                override fun onFail(errCode: Int, errMsg: String) {
                    Log.d("DATA===", errMsg)
                    ToastUtil.showToast("请检查网络是否打开")
                }
            })
    }

    fun getList() {
        ViseHttp.GET("api/today")
            .setHttpCache(true)
            .request(object : ACallback<HttpModel<Map<String, List<Map<String, Any?>>>>>() {
                override fun onSuccess(data: HttpModel<Map<String, List<Map<String, Any?>>>>) {
                    val category = data.category!!
                    if (category.contains("福利"))
                        category.removeAt(category.indexOf("福利"))
                    if (category.contains("休息视频"))
                        category.removeAt(category.indexOf("休息视频"))
                    val l = ArrayList<GankBean>()
                    for (c in category) {
                        val a = data.results!!.getValue(c).reversed()
                        val images = if (a[0]["images"] == null) null else a[0]["images"] as List<String>
                        val bean = GankBean(
                            a[0]["_id"].toString(),
                            a[0]["publishedAt"].toString().substring(0, 10),
                            a[0]["type"].toString(),
                            a[0]["desc"].toString(),
                            a[0]["who"].toString(),
                            a[0]["url"].toString(),
                            if (images == null) "" else images[0]
                        )
                        l.add(bean)
                    }
                    list.postValue(l)
                }

                override fun onFail(errCode: Int, errMsg: String) {
                    Log.d("DATA===", errMsg)
                    ToastUtil.showToast("请检查网络是否打开")
                }
            })
    }
}
