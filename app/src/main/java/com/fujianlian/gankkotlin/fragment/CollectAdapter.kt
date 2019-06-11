package com.fujianlian.gankkotlin.fragment

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import androidx.databinding.BindingAdapter
import com.fujianlian.gankkotlin.R
import com.fujianlian.gankkotlin.databinding.GankItemBinding
import androidx.databinding.DataBindingUtil
import androidx.annotation.NonNull
import com.fujianlian.gankkotlin.bean.CollectBean

class CollectAdapter(private val list: List<CollectBean>) : RecyclerView.Adapter<CollectAdapter.ViewHolder>() {

    @BindingAdapter("app:imageId")
    fun ImageView.getInternetImage(@NonNull url: String) {
        this.setImageURI(Uri.parse(url))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gank_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var mBinding: GankItemBinding? = null

        init {
            mBinding = DataBindingUtil.bind(itemView)
        }

        fun bind(@NonNull bean: CollectBean) {
            mBinding?.bean = bean
        }
    }

}