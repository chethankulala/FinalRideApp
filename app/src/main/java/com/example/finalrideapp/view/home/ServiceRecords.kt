package com.example.finalrideapp.view.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalrideapp.Adapters.ServiceRecordsAdapter
import com.example.finalrideapp.R
import com.example.finalrideapp.model.DataModels.ServiceRecordModel
import kotlinx.android.synthetic.main.activity_service_records.*
import java.util.ArrayList

class ServiceRecords : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_records)

        var toolbar = findViewById(R.id.tbServiceRecord) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.Recycler)

        val dataList = ArrayList<ServiceRecordModel>()
        dataList.add(ServiceRecordModel("15", "NOV 2017","General service"))
        dataList.add(ServiceRecordModel("16", "DEC 2016", "Breakdown"))
        dataList.add(ServiceRecordModel("17", "MAY 2013", "Free service"))
        dataList.add(ServiceRecordModel("18", "AUG 2015", "General service"))

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val rvAdapter = ServiceRecordsAdapter(dataList)
        recyclerView.adapter = rvAdapter

        supportActionBar?.elevation = 0F
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#ED7E2B")))

        val category2: List<String> = arrayListOf("Vehicle Type","Free service","General service","Breakdown")
        val category1: List<String> = arrayListOf("Service Type","Classic 350- Black","Thunderbird 350-Blue","Bullet 350-Black")
        val dataAdapter1: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, category1)
        val dataAdapter2: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, category2)
        spinner1.adapter = dataAdapter1
        spinner1TextView.text = "Vehicle Type"
        spinner2.adapter = dataAdapter2
        spinner2TextView.text = "Service Type"

        // setContentView(R.layout.spinner_item)
        // val simpleRatingBar = findViewById(R.id.ratingBar) as RatingBar
        //simpleRatingBar.setRating(5F)

    }
}
