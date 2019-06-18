package com.fujianlian.gankkotlin.fragment.collect

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fujianlian.gankkotlin.R
import com.fujianlian.gankkotlin.bean.GankBean
import com.fujianlian.gankkotlin.databinding.GankItemBinding

class CollectAdapter(private val list: List<GankBean>) : RecyclerView.Adapter<CollectAdapter.ViewHolder>() {

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

        fun bind(@NonNull bean: GankBean) {
            mBinding?.bean = bean
        }
    }

}