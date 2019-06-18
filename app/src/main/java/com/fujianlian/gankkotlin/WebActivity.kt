package com.fujianlian.gankkotlin

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fujianlian.gankkotlin.bean.GankBean
import com.fujianlian.gankkotlin.util.database
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : BaseActivity() {

    private var favoriteItem: MenuItem?=null
    private lateinit var closeItem: MenuItem
    private val fromList by lazy {
        intent.getBooleanExtra("fromList", false)
    }
    private val bean: GankBean by lazy {
        intent.getParcelableExtra<GankBean>("bean")
    }
    private lateinit var viewModel: WebViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        webView.webViewClient = webViewClient
        webView.webChromeClient = webChromeClient
        if (fromList) {
            viewModel = ViewModelProviders.of(this).get(WebViewModel::class.java)
            viewModel.bean = bean
            webView.loadUrl(bean.url)
            observe()
            if (!bean.isCollect) {
                viewModel.checkCollect(database)
            }
        } else {
            title =
                if (intent.getStringExtra("title") == null) ""
                else intent.getStringExtra("title")
            webView.loadUrl(intent.getStringExtra("url"))
        }
    }

    private fun observe() {
        viewModel.isCollect.observe(this, Observer<Boolean> {
            if (it) {
                bean.isCollect = it
                favoriteItem?.setIcon(R.drawable.ic_favorite_white_24dp)
            }
        })
        viewModel.isDelete.observe(this, Observer<Boolean> {
            if (it) {
                bean.isCollect = false
                favoriteItem?.setIcon(R.drawable.ic_favorite_border_white_24dp)
            }
        })
        viewModel.insertCollect.observe(this, Observer<Boolean> {
            if (it) {
                bean.isCollect = true
                favoriteItem?.setIcon(R.drawable.ic_favorite_white_24dp)
            }
        })
    }

    private val webViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            closeItem.isVisible = true
            return false
        }
    }

    private val webChromeClient = object : WebChromeClient() {
        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
            setTitle(title)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.web_menu, menu)
        favoriteItem = menu.findItem(R.id.favorite)
        closeItem = menu.findItem(R.id.close)
        if (!fromList) {
            favoriteItem?.isVisible = false
        } else if (bean.isCollect) {
            favoriteItem?.setIcon(R.drawable.ic_favorite_white_24dp)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.close -> {
                finish()
            }
            R.id.favorite -> {
                if (bean.isCollect) {
                    viewModel.deleteCollect(database)
                } else {
                    viewModel.addCollect(database)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
