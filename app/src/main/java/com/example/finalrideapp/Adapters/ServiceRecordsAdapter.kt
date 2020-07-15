package com.example.finalrideapp.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalrideapp.R
import com.example.finalrideapp.model.DataModels.ServiceRecordModel
import com.example.finalrideapp.view.home.BookingServiceDetails
import kotlinx.android.synthetic.main.service_records_items.view.*

class ServiceRecordsAdapter(val userList: ArrayList<ServiceRecordModel>) : RecyclerView.Adapter<ServiceRecordsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.service_records_items, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        p0.date?.text = userList[p1].date.toString()
        p0.day?.text = userList[p1].day
        p0.service?.text = userList[p1].service


    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date = itemView.findViewById<TextView>(R.id.text1)
        val day = itemView.findViewById<TextView>(R.id.text2)
        val service = itemView.findViewById<TextView>(R.id.service)
        init {


            itemView.relative.setOnClickListener {
                nextActivity(it)


            }
        }

        private fun nextActivity(it: View) {
            val intent: Intent = Intent(itemView.context, BookingServiceDetails::class.java)
            it.context.startActivity(intent)
        }
    }
}