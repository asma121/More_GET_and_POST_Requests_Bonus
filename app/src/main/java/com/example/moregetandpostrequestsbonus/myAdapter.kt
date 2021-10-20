package com.example.moregetandpostrequestsbonus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class myAdapter(private val info:ArrayList<ArrayList<String>>): RecyclerView.Adapter<myAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val name= info[position][0]
        val location= info[position][1]
        val mobile= info[position][2]
        val email= info[position][3]
        holder.itemView.apply {
          textView.text=name+"\n"+location+"\n"+mobile+"\n"+email
        }
    }

    override fun getItemCount()=info.size
}