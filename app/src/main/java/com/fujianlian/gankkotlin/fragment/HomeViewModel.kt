package com.fujianlian.gankkotlin.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fujianlian.gankkotlin.bean.GankBean
import com.fujianlian.gankkotlin.util.HttpModel
import com.vise.xsnow.http.ViseHttp
import com.vise.xsnow.http.callback.ACallback

class HomeViewModel : ViewModel() {

    val list: MutableLiveData<List<GankBean>> = MutableLiveData()

    fun getList() {
        ViseHttp.GET("api/data/福利/5/1")
            .request(object : ACallback<HttpModel<List<GankBean>>>() {
                override fun onSuccess(data: HttpModel<List<GankBean>>) {
                    list.postValue(data.results!!)
                }

                override fun onFail(errCode: Int, errMsg: String) {
                    Log.d("DATA===", "fail")
                }
            })
    }
}
