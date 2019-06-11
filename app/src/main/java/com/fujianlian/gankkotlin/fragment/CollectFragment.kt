package com.fujianlian.gankkotlin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.fujianlian.gankkotlin.R
import com.fujianlian.gankkotlin.bean.CollectBean
import com.fujianlian.gankkotlin.util.database
import kotlinx.android.synthetic.main.fragment_collect.*

class CollectFragment : Fragment() {

    private val adapter by lazy { CollectAdapter(list) }
    private val list: ArrayList<CollectBean> = ArrayList()
    private lateinit var viewModel: CollectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_collect, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CollectViewModel::class.java)
        viewpager.adapter = adapter

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
