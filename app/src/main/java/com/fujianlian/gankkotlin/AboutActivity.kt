package com.fujianlian.gankkotlin

import android.os.Bundle

class AboutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        title = "关于"
    }
}
