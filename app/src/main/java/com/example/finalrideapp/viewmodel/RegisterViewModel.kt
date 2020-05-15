package com.example.finalrideapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.finalrideapp.model.db.entities.User
import com.example.finalrideapp.model.repositories.UserRepository
import com.example.finalrideapp.util.*
import com.example.finalrideapp.view.auth.AuthListener
import java.text.SimpleDateFormat
import java.util.*

class RegisterViewModel(private val repository: UserRepository): ViewModel() {

    var name: String? = null
    var phone: String? = null
    var email: String? = null
    var pass: String? = null

    var authListener: AuthListener? = null

    fun registerOnClick() {
        Log.d("hello","started")
        authListener?.onStarted()

        if(name.isNullOrEmpty()){
            authListener?.inputValidation(1, "Enter Valid Name")
            return
        }

        if(phone.isNullOrEmpty() || isPhoneValid(phone.toString())){
            authListener?.inputValidation(2,"Enter Valid Phone")
            return
        }

        if(email.isNullOrEmpty() || isEmailValid(email.toString())){
            Log.d("email", email.toString())
            authListener?.inputValidation(3, "Enter Valid Email")
            return
        }

        if(pass.isNullOrEmpty() || isPasswordValid(pass.toString())) {
            authListener?.inputValidation(4,"Enter Valid Password")
            return
        }

        Coroutines.main {
            try {
                val authResponse = repository.funUserRegister(name.toString(), email.toString(), phone.toString(), pass.toString())
                Log.d("hello","started")
                authResponse.data?.let {
                    val token = authResponse.data.get("token").toString()
                    Log.d("token", token)
                    authListener?.onSuccess()
                    val startTime: String = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(
                        Date()
                    )
                    val userDetails: User = User(startTime, null, name.toString(), email.toString(), phone.toString(), pass.toString(), null)
                    repository.saveUser(userDetails)
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