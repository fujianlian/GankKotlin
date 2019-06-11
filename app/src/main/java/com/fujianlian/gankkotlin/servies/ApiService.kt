package com.fujianlian.gankkotlin.servies

import com.fujianlian.gankkotlin.bean.GankBean
import io.reactivex.Observable
import me.goldze.mvvmhabit.http.BaseResponse
import retrofit2.http.*

interface ApiService {

    @GET("api/data/{type}/16/{page}")
    fun getList(@Path("type") type: String, @Path("page") page: Int): Observable<BaseResponse<GankBean>>

    @FormUrlEncoded
    @POST("action/apiv2/banner")
    fun demoPost(@Field("catalog") catalog: String): Observable<BaseResponse<GankBean>>
}