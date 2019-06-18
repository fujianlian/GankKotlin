package com.fujianlian.gankkotlin

import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : BaseActivity() {

    private val url by lazy {  intent.getStringExtra("url") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        title = intent.getStringExtra("title")
        Glide
            .with(image)
            .load(url)
            .centerCrop()
            .into(image)
    }
}
