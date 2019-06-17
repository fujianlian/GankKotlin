package com.fujianlian.gankkotlin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.fujianlian.gankkotlin.R
import com.fujianlian.gankkotlin.bean.BannerInfo
import com.fujianlian.gankkotlin.bean.CollectBean
import com.fujianlian.gankkotlin.util.database
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val adapter by lazy { HomeAdapter(list,imagesUrl) }
    private val list: ArrayList<CollectBean> = ArrayList()
    private val imagesUrl: ArrayList<BannerInfo> = ArrayList()
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
        imagesUrl.add(BannerInfo("测试1", "https://ww1.sinaimg.cn/large/0065oQSqly1g2pquqlp0nj30n00yiq8u.jpg"))
        imagesUrl.add(BannerInfo("测试2", "https://ww1.sinaimg.cn/large/0065oQSqly1g2hekfwnd7j30sg0x4djy.jpg"))
        imagesUrl.add(BannerInfo("测试3", "https://ws1.sinaimg.cn/large/0065oQSqly1g0ajj4h6ndj30sg11xdmj.jpg"))
        imagesUrl.add(BannerInfo("测试4", "https://ws1.sinaimg.cn/large/0065oQSqly1fytdr77urlj30sg10najf.jpg"))
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        viewModel.list.observe(this, Observer<List<CollectBean>> {
            list.clear()
            list.addAll(it)
            if (list.size > 0)
                adapter.notifyDataSetChanged()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getList(activity!!.database)
    }
}



