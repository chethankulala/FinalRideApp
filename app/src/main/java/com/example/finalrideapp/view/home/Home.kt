package com.example.finalrideapp.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.finalrideapp.R
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //get nav controller
        navigationController = Navigation.findNavController(this, R.id.host_fragment)

        //Setting the navigation controller to Bottom Nav
        navBar.setupWithNavController(navigationController)

        //set with action bar
        NavigationUI.setupActionBarWithNavController(this,navigationController)

    }
}
