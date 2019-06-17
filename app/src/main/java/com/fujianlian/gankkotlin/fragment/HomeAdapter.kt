package com.fujianlian.gankkotlin.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.fujianlian.gankkotlin.R
import com.fujianlian.gankkotlin.bean.BannerInfo
import com.fujianlian.gankkotlin.bean.CollectBean
import com.fujianlian.gankkotlin.databinding.GankItemBinding
import com.fujianlian.gankkotlin.view.MyXBanner

class HomeAdapter(private val list: List<CollectBean>, private val banners: List<BannerInfo>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = if (viewType == 1) {
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_top, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.gank_item, parent, false)
        }
        return ViewHolder(view, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) 1 else 2
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            holder.myXBanner?.loadImage { _, _, view, p ->
                val image = view as SimpleDraweeView
                image.setImageURI(banners[p].xBannerUrl)
            }
            holder.myXBanner?.setBannerData(R.layout.image_fresco, banners)
        } else {
            holder.bind(list[position - 1])
        }
    }

    inner class ViewHolder internal constructor(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {
        private var mBinding: GankItemBinding? = null
        var myXBanner: MyXBanner? = null

        init {
            if (viewType == 1) {
                myXBanner = itemView as MyXBanner
            } else {
                mBinding = DataBindingUtil.bind(itemView)
            }
        }

        fun bind(@NonNull bean: CollectBean) {
            mBinding?.bean = bean
        }
    }

}