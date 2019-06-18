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
import com.fujianlian.gankkotlin.bean.GankBean
import com.fujianlian.gankkotlin.util.database
import kotlinx.android.synthetic.main.fragment_collect.*

class CollectFragment : Fragment() {

    private val adapter by lazy { CollectAdapter(list) }
    private val list: ArrayList<GankBean> = ArrayList()
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
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        viewModel.list.observe(this, Observer<List<GankBean>> {
            list.clear()
            list.addAll(it)
            textView.visibility =
                if (list.isNotEmpty()) View.GONE
                else View.VISIBLE
            adapter.notifyDataSetChanged()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getList(activity!!.database)
    }
}
