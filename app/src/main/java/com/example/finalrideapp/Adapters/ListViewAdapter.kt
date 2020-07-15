package com.example.finalrideapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.finalrideapp.R


class ListViewAdapter(context:Context?, titleList: Array<String>, imagesList: Array<Int>): BaseAdapter() {
    val context: Context?
    val titleList: Array<String>
    val imagesList: Array<Int>
    init {
        this.context = context
        this.titleList = titleList
        this.imagesList = imagesList
    }



    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.listview_items, parent, false)
        val itemTitle = view.findViewById(R.id.tvTitle) as TextView
        val itemIcon = view.findViewById(R.id.listviewitemIcon) as ImageView
        itemTitle.setText(titleList[position])
        itemIcon.setImageResource(imagesList[position])
        return view

    }

    override fun getItem(position: Int): Any {
          return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        //if (titleList == null) 0 else
        return titleList.size
    }
}