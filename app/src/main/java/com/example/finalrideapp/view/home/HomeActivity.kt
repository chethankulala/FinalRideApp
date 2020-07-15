package com.example.finalrideapp.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.finalrideapp.R
import com.example.finalrideapp.fragment.MyGarageFragment
import com.example.finalrideapp.fragment.WelcomeFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_home)

        /*
        navBar.setOnNavigationItemSelectedListener { item ->
            var fragment: Fragment? = null
            when (item.itemId) {
                R.id.nav_trips_fragment -> fragment = WelcomeFragment()
                R.id.nav_my_garage_fragment -> fragment = MyGarageFragment()
            }

            supportFragmentManager
                .beginTransaction()
                .addToBackStack(fragment!!.tag)
                .replace(R.id.host_fragment, fragment)
                .commit()

            true
        }

         */


        //get nav controller
        navigationController = Navigation.findNavController(this, R.id.host_fragment)

        //Setting the navigation controller to Bottom Nav
        navBar.setupWithNavController(navigationController)

        //set with action bar
        NavigationUI.setupActionBarWithNavController(this,navigationController)

    }
}
