package com.fujianlian.gankkotlin.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fujianlian.gankkotlin.AboutActivity

import com.fujianlian.gankkotlin.R
import com.fujianlian.gankkotlin.WebActivity
import kotlinx.android.synthetic.main.fragment_my.*
import org.jetbrains.anko.startActivity

class MyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        githubRL.setOnClickListener {
            activity?.startActivity<WebActivity>("url" to "https://github.com/fujianlian")
        }
        pRL.setOnClickListener {
            activity?.   startActivity<WebActivity>("url" to "https://github.com/fujianlian/GankKotlin/issues")
        }
        aboutRL.setOnClickListener {
            activity?.startActivity<AboutActivity>()
        }
    }

}
