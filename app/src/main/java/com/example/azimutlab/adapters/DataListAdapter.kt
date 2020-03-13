package com.example.azimutlab.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.azimutlab.R
import com.example.azimutlab.mvp.models.PostModel
import kotlinx.android.synthetic.main.data_list_item.view.*

class DataListAdapter : RecyclerView.Adapter<DataListAdapter.ViewHolder>() {

    private var dataList = ArrayList<PostModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.data_list_item, parent, false)
        return ViewHolder(itemView)
    }


    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = dataList[position]
        holder.bind(post)
    }


    fun addDataList(data: ArrayList<PostModel>) {
        dataList.addAll(data)
    }


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(item: PostModel) {
            itemView.apply {
                user_id.text = item.userId.toString()
                title.text = item.title
                post_id.text = item.id.toString()
                content.text = item.body
            }
        }

    }

}