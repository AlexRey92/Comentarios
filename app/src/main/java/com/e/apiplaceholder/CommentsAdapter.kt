package com.e.apiplaceholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CommentsAdapter:ListAdapter<Comments,CommentsAdapter.ViewHolder>(DiffCallBack) {
    inner class ViewHolder(val view:View):RecyclerView.ViewHolder(view){
        val postiD:TextView=view.findViewById(R.id.textViewIdPost)
        val id:TextView=view.findViewById(R.id.textViewID)
        val name:TextView=view.findViewById(R.id.textViewTitle)
        val body:TextView=view.findViewById(R.id.textViewBody)

        fun onBind(comentario:Comments){
            postiD.text=comentario.userId.toString()
            id.text=comentario.id.toString()
            name.text=comentario.title
            body.text=comentario.body
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val myview:View=LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false)
        return ViewHolder(myview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bindi=getItem(position)
        holder.onBind(bindi)

        }

    companion object DiffCallBack : DiffUtil.ItemCallback<Comments>() {
        override fun areItemsTheSame(oldItem: Comments, newItem: Comments): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Comments, newItem: Comments): Boolean {
            return oldItem == newItem
        }
    }
}