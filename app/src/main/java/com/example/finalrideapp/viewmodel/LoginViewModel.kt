package com.example.finalrideapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.finalrideapp.model.db.entities.User
import com.example.finalrideapp.model.network.responses.AuthResponse
import com.example.finalrideapp.model.repositories.UserRepository
import com.example.finalrideapp.util.*
import com.example.finalrideapp.view.auth.AuthListener
import java.text.SimpleDateFormat
import java.util.*

class LoginViewModel(private val repository: UserRepository): ViewModel() {

    var emailOrPhone: String? = null
    var pass: String? = null

    var authListener: AuthListener? = null

    //fun getUserDetails() = repository.getUser()

    /*
    fun getNewToken(email: String, pass: String) {

        Coroutines.main {
            try {
                val newTokenResponse = repository.funUserLoginEmail(email, pass)
            }

        }

    }

     */


    fun loginOnClick() {
        authListener?.onStarted()

        if(emailOrPhone.isNullOrEmpty()){
            authListener?.inputValidation(1,"Enter Valid Phone or email")
            return
        }

        if(isPhoneValid(emailOrPhone.toString())){
            authListener?.inputValidation(1,"Enter Valid Phone")
            return
        }
        if(isEmailValid(emailOrPhone.toString())){
            authListener?.inputValidation(1, "Enter Valid Email")
            return
        }
        if(pass.isNullOrEmpty() || isPasswordValid(pass.toString())) {
            authListener?.inputValidation(2,"Enter Valid Password")
            return
        }


        Coroutines.main {
            try {
                var authResponse: AuthResponse
                if (isPhoneValid(emailOrPhone.toString())) {
                    authResponse = repository.funUserLoginPhone(emailOrPhone.toString(), pass.toString())
                } else {
                    authResponse = repository.funUserLoginEmail(emailOrPhone.toString(), pass.toString())
                }


                //val authResponse = repository.funUserLoginEmail(emailOrPhone.toString(), pass.toString())
                authResponse.data?.let {
                    val token = authResponse.data?.get("token").toString()
                    Log.d("token", token)
                    authListener?.onSuccess()
                    //val userDetails: LiveData<User> = getUserDetails()
                    //val userDetails: User = User(startTime, null, name.toString(), email.toString(), phone.toString(), pass.toString(), null)
                    val current: String = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
                    repository.saveToken(token, current)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch(e: ApiException){
                authListener?.onFailure(e.message!!)
            }catch (e: NoInternetException){
                authListener?.onFailure(e.message!!)
            }
        }
    }
}
