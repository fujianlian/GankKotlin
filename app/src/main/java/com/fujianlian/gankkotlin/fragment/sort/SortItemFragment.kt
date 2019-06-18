package com.fujianlian.gankkotlin.fragment.sort

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
import com.fujianlian.gankkotlin.fragment.collect.CollectAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class SortItemFragment : Fragment() {

    private lateinit var viewModel: SortItemViewModel

    private var index = 1

    private val adapter by lazy { CollectAdapter(list) }
    private val list: ArrayList<GankBean> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SortItemViewModel::class.java).apply {
            type = arguments?.getString(GANK_TYPE) ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        viewModel.list.observe(this, Observer<List<GankBean>> {
            if (index == 1)
                list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        })
        viewModel.getList(index)
    }

    companion object {

        private const val GANK_TYPE = "section_type"

        @JvmStatic
        fun newInstance(type: String): SortItemFragment {
            return SortItemFragment().apply {
                arguments = Bundle().apply {
                    putString(GANK_TYPE, type)
                }
            }
        }
    }
}