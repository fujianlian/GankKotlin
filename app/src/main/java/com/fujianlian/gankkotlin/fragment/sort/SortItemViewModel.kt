package com.fujianlian.gankkotlin.fragment.sort

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fujianlian.gankkotlin.bean.GankBean
import com.fujianlian.gankkotlin.util.HttpModel
import com.fujianlian.gankkotlin.util.ToastUtil
import com.vise.xsnow.http.ViseHttp
import com.vise.xsnow.http.callback.ACallback

class SortItemViewModel : ViewModel() {

    var type = ""
    val list: MutableLiveData<List<GankBean>> = MutableLiveData()

    fun getList(index: Int) {
        Log.e("type===", type)
        ViseHttp.GET("api/data/$type/10/$index")
            .setHttpCache(true)
            .request(object : ACallback<HttpModel<List<GankBean>>>() {
                override fun onSuccess(data: HttpModel<List<GankBean>>) {
                    data.results!!.map {
                        if (it.images == null) {
                            it.image = ""
                        } else {
                            it.image = it.images!![0]
                            it.images!!.clear()
                        }
                    }
                    list.postValue(data.results!!)
                }

                override fun onFail(errCode: Int, errMsg: String) {
                    Log.d("DATA===", errMsg)
                    ToastUtil.showToast("请检查网络是否打开")
                }
            })
    }
}