package com.fujianlian.gankkotlin

import android.app.Application
import com.vise.log.ViseLog
import com.vise.log.inner.LogcatTree
import com.vise.xsnow.http.ViseHttp

class GankApplication :Application() {

    override fun onCreate() {
        super.onCreate()
        initNetLog()
        initNet()
    }

    private fun initNetLog() {
        ViseLog.getLogConfig()
            .configAllowLog(true)//是否输出日志
            .configShowBorders(false)//是否排版显示
        ViseLog.plant(LogcatTree())//添加打印日志信息到Logcat的树
    }

    private fun initNet() {
        ViseHttp.init(this)
        ViseHttp.CONFIG()
            //配置请求主机地址
            .baseUrl("https://gank.io/")
    }
}