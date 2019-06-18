package com.fujianlian.gankkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.fujianlian.gankkotlin.fragment.collect.CollectFragment
import com.fujianlian.gankkotlin.fragment.home.HomeFragment
import com.fujianlian.gankkotlin.fragment.MyFragment
import com.fujianlian.gankkotlin.fragment.sort.SortFragment
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val list = ArrayList<Fragment>()
    private val list1 = ArrayList<Int>()

    private val adapter by lazy {
        object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return list[position]
            }

            override fun getCount(): Int {
                return list.size
            }
        }
    }

    private val onNavigationItemSelectedListener = OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                setTitle(R.string.title_home)
                myViewPager.setCurrentItem(0, false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                setTitle(R.string.title_dashboard)
                myViewPager.setCurrentItem(1, false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_collect -> {
                setTitle(R.string.title_collect)
                myViewPager.setCurrentItem(2, false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_person -> {
                setTitle(R.string.title_person)
                myViewPager.setCurrentItem(3, false)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list.add(HomeFragment())
        list.add(SortFragment())
        list.add(CollectFragment())
        list.add(MyFragment())
        list1.add(R.id.navigation_home)
        list1.add(R.id.navigation_dashboard)
        list1.add(R.id.navigation_collect)
        list1.add(R.id.navigation_person)
        setContentView(R.layout.activity_main)
        myViewPager.offscreenPageLimit = 3
        myViewPager.adapter = adapter
        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
