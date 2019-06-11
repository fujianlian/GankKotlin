package com.fujianlian.gankkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.fujianlian.gankkotlin.fragment.CollectFragment
import com.fujianlian.gankkotlin.fragment.HomeFragment
import com.fujianlian.gankkotlin.fragment.MyFragment
import com.fujianlian.gankkotlin.fragment.SortFragment
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val list = ArrayList<Fragment>()
    private val list1 = ArrayList<Int>()

    private val adapter by lazy {
        object : FragmentStateAdapter(this) {

            override fun getItemCount(): Int {
                return list.size
            }

            override fun createFragment(position: Int): Fragment {
                return list[position]
            }

        }
    }

    private val onNavigationItemSelectedListener = OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                setTitle(R.string.title_home)
                viewpager2.setCurrentItem(0, false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                setTitle(R.string.title_dashboard)

                viewpager2.setCurrentItem(1, false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_collect -> {
                setTitle(R.string.title_collect)

                viewpager2.setCurrentItem(2, false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_person -> {
                setTitle(R.string.title_person)

                viewpager2.setCurrentItem(3, false)
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
        viewpager2.adapter = adapter
        viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                nav_view.selectedItemId = list1[position]
            }
        })
        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }


}
