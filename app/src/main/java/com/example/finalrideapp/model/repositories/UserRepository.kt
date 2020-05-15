package com.example.finalrideapp.model.repositories

import android.util.Log
import com.example.finalrideapp.model.db.AppDatabase
import com.example.finalrideapp.model.db.entities.User
import com.example.finalrideapp.model.network.MyApi
import com.example.finalrideapp.model.network.SafeApiRequest
import com.example.finalrideapp.model.network.responses.AuthResponse

class UserRepository (
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun funUserRegister(
        name: String,
        email: String,
        phone: String,
        password: String
    ) : AuthResponse {
        return apiRequest{ api.userRegister(name, email, phone, password) }
    }

    suspend fun funUserLoginPhone(
        phone: String,
        password: String
    ) : AuthResponse {
        return apiRequest{ api.userLoginPhone(phone, password) }
    }

    suspend fun funUserLoginEmail(
        email: String,
        password: String
    ) : AuthResponse {
        return apiRequest{ api.userLoginEmail(email, password) }
    }


    suspend fun saveUser(user: User): Long = db.getUserDao().upsert(user)

    suspend fun saveToken(token: String, current: String): Int = db.getUserDao().updateToken(token, current)

    //fun updateImagePath(path: String): Int = db.getUserDao().updatePath(path)

    fun getUser() = db.getUserDao().getuser()

}