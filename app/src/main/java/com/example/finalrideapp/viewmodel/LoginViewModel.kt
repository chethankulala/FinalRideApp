package com.example.finalrideapp.viewmodel

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalrideapp.model.db.entities.User
import com.example.finalrideapp.model.network.responses.AuthResponse
import com.example.finalrideapp.model.preferences.PreferenceProvider
import com.example.finalrideapp.model.repositories.NewUserRepository
import com.example.finalrideapp.model.repositories.UserRepository
import com.example.finalrideapp.util.*
import com.example.finalrideapp.view.auth.AuthListener
import com.example.finalrideapp.view.auth.OtpVerify
import java.text.SimpleDateFormat
import java.util.*

class LoginViewModel(private val repository: NewUserRepository): ViewModel() {

    var emailOrPhone: String? = null
    var pass: String? = null
    var forgotOnClick = MutableLiveData<String>()

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
            authListener?.inputValidation(1,"Enter Phone or email")
            return
        }

        if(!isPhoneValid(emailOrPhone.toString()) && !isEmailValid(emailOrPhone.toString())){
            authListener?.inputValidation(1,"Enter Valid Phone or email")
            return
        }

        if(pass.isNullOrEmpty()) {
            authListener?.inputValidation(2,"Enter Password")
            return
        }
        if (!isPasswordValid(pass.toString())) {
            authListener?.inputValidation(2,"Enter Valid Password")
            return
        }


        Coroutines.main {
            try {
                val authResponse: AuthResponse
                if (isPhoneValid(emailOrPhone.toString())) {
                    authResponse = repository.funUserLoginPhone(emailOrPhone.toString(), pass.toString())
                } else {
                    authResponse = repository.funUserLoginEmail(emailOrPhone.toString(), pass.toString())
                }


                //val authResponse = repository.funUserLoginEmail(emailOrPhone.toString(), pass.toString())
                authResponse.data?.let {
                    var accessToken  = authResponse.data.get("accessToken").toString()
                    accessToken = accessToken.substring(1, accessToken.length-1)
                    var refreshToken  = authResponse.data.get("refreshToken").toString()
                    refreshToken = refreshToken.substring(1, refreshToken.length-1)
                    val currentTime: String = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.US).format(Date())
                    //Log.d("otpVerificationID", accessToken)
                    //Log.d("otpVerificationID", refreshToken)
                    //Log.d("currentTime", currentTime)
                    repository.saveDetails(currentTime, accessToken, refreshToken)
                    authListener?.onSuccess()
                    //val userDetails: LiveData<User> = getUserDetails()
                    //val userDetails: User = User(startTime, null, name.toString(), email.toString(), phone.toString(), pass.toString(), null)

                    //val result = repository.saveToken(token, current)
                    /*
                    Log.d("Login time", current)
                    Log.d("Login Result", result.toString())
                    if (result != 0) {
                        Log.d("Login Token", "Saved")
                    }

                     */

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

    fun forgotPasswordOnClick () {

        if(emailOrPhone.isNullOrEmpty()){
            authListener?.inputValidation(1,"Enter Phone or email")
            return
        }

        Coroutines.main {
            try {
                val authResponse: AuthResponse
                if (isPhoneValid(emailOrPhone.toString())) {
                    authResponse = repository.funForgotPasswordPhone(emailOrPhone.toString())
                } else {
                    authResponse = repository.funForgotPasswordEmail(emailOrPhone.toString())
                }


                //val authResponse = repository.funUserLoginEmail(emailOrPhone.toString(), pass.toString())
                authResponse.data?.let {
                    var otpVerificationID  = authResponse.data.get("otpVerificationID").toString()
                    otpVerificationID = otpVerificationID.substring(1, otpVerificationID.length-1)
                    //Log.d("otpVerificationID", otpVerificationID)
                    repository.saveOtpVerificationID(otpVerificationID)

                    forgotOnClick.value = "success"

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
