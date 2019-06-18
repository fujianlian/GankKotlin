package com.fujianlian.gankkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.fujianlian.gankkotlin.R
import com.fujianlian.gankkotlin.bean.GankBean
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val adapter by lazy { HomeAdapter(list,imagesUrl) }
    private val list: ArrayList<GankBean> = ArrayList()
    private val imagesUrl: ArrayList<GankBean> = ArrayList()
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        viewModel.bannerList.observe(this, Observer<List<GankBean>> {
            imagesUrl.clear()
            imagesUrl.addAll(it)
            if (imagesUrl.size > 0)
                adapter.notifyItemChanged(0)
        })
        viewModel.list.observe(this, Observer<List<GankBean>> {
            list.clear()
            list.addAll(it)
        })
        viewModel.getBannerList()
        viewModel.getList()
    }

}



