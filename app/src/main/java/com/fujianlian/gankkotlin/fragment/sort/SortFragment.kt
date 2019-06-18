package com.fujianlian.gankkotlin.fragment.sort

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentPagerAdapter

import com.fujianlian.gankkotlin.R
import kotlinx.android.synthetic.main.fragment_sort.*

class SortFragment : Fragment() {

    private val tabTitles = arrayOf(
        "全部",
        "Android",
        "iOS",
        "App",
        "前端",
        "拓展资源",
        "瞎推荐"
    )

    private val adapter by lazy {
        object : FragmentPagerAdapter(fragmentManager) {

            override fun getItem(position: Int): Fragment {
                if (position == 0)
                    return SortItemFragment.newInstance("all")
                return SortItemFragment.newInstance(tabTitles[position])
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return tabTitles[position]
            }

            override fun getCount(): Int {
                return tabTitles.size
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sort, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.offscreenPageLimit = tabTitles.size - 1
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

}
