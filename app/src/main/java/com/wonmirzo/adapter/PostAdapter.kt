package com.wonmirzo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wonmirzo.R
import com.wonmirzo.activity.MainActivity
import com.wonmirzo.model.Poster

class PostAdapter(var activity: MainActivity, private var items: ArrayList<Poster>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_post_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is MyViewHolder) {
            val tvTitle = holder.tvTitle
            val tvBody = holder.tvBody

            tvTitle.text = item.title
            tvBody.text = item.body

            val llPoster = holder.llPoster
            llPoster.setOnLongClickListener {
                activity.dialogPoster(item)
                false
            }
        }
    }

    override fun getItemCount(): Int = items.size

    //viewHolder
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvBody: TextView = view.findViewById(R.id.tvBody)
        val llPoster: LinearLayout = view.findViewById(R.id.llPoster)
    }
}