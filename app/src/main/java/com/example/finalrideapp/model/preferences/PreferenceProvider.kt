package com.example.finalrideapp.model.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log

private const val Otp_Verification_ID = "otp_verification_id"
//private const val KEY_SAVED_DETAILS = "key_saved_details"
private const val PATH_SAVED = "path_saved"
private const val UPDATE_TIME = "update_time"
private const val ACCESS_TOKEN = "access_token"
private const val REFRESH_TOKEN = "refresh_token"

class PreferenceProvider(
    context: Context
) {

    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)


    fun saveOtpVerificationID(otpVerificationID: String) {
        preference.edit().putString(
            Otp_Verification_ID,
            otpVerificationID
        ).apply()
    }

    fun getOtpVerificationID(): String? {
        return preference.getString(Otp_Verification_ID, null)
    }

    fun saveDetails(time: String, accessToken: String, refreshToken: String) {
        val editor:SharedPreferences.Editor =  preference.edit()
        editor.putString(UPDATE_TIME, time)
        editor.putString(ACCESS_TOKEN, accessToken)
        editor.putString(REFRESH_TOKEN, refreshToken)
        editor.apply()
        editor.commit()
    }

    fun getTime(): String? {
        return preference.getString(UPDATE_TIME, null)
    }

    fun getAccessToken(): String? {
        return preference.getString(ACCESS_TOKEN, null)
    }

    fun getRefreshToken(): String? {
        return preference.getString(REFRESH_TOKEN, null)
    }

    fun savePath(path: String) {
        preference.edit().putString(
            PATH_SAVED,
            path
        ).apply()
    }

    fun getPath(): String? {
        return preference.getString(PATH_SAVED, null)
    }
}