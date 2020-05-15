package com.example.finalrideapp.view.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.finalrideapp.R
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        val resetToolbar: Toolbar = findViewById(R.id.resetToolbar) as Toolbar
        setSupportActionBar(resetToolbar)
        supportActionBar?.title = " "
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnResetPassword.setOnClickListener() {
            val nextResetSuccessPage = Intent(this, ResetPasswordSuccess::class.java)
            startActivity(nextResetSuccessPage)
        }
    }
}
