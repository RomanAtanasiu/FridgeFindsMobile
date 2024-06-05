package com.example.fridgefindsmobile

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val mData: MutableList<Item>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val myCheckBox: CheckBox = itemView.findViewById(R.id.checkBox)
        val myTextView: TextView = itemView.findViewById(R.id.textViewAdapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }


    private fun toggleStrikedItem(textViewAdapter: TextView, isChecked: Boolean){
        if(isChecked){
            textViewAdapter.paintFlags = textViewAdapter.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            textViewAdapter.paintFlags = textViewAdapter.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
/*
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mData[position]
        holder.myTextView.text = item.text
        holder.myCheckBox.setOnCheckedChangeListener(null)
        holder.myCheckBox.isChecked = item.isChecked
        holder.myCheckBox.setOnCheckedChangeListener { _, isChecked ->
            item.isChecked = isChecked
        }
    }
    */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val new_item = mData[position]
        holder.itemView.apply {
            holder.myTextView.text = new_item.text
            holder.myCheckBox.isChecked = new_item.isChecked
            toggleStrikedItem(holder.myTextView, new_item.isChecked)
            holder.myCheckBox.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikedItem(holder.myTextView, isChecked)
                new_item.isChecked = !new_item.isChecked
            }
        }
    }






    fun addItem(item: Item) {
        mData.add(item)
        notifyItemInserted(mData.size - 1)
        println(mData)

    }

    fun deleteItems() {
        mData.removeAll { item -> item.isChecked }
        notifyDataSetChanged()
    }


}