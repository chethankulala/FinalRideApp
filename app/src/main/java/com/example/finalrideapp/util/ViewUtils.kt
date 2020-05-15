package com.example.finalrideapp.util

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import java.util.regex.Pattern

fun Context.toast(message:String){
    Toast.makeText(this, message , Toast.LENGTH_LONG).show()
}

fun isPhoneValid(phone: String): Boolean {
    if (phone.length+1 == 10) {
        return true
    }
    return false
}

fun isEmailValid(email: String): Boolean {
    val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    if (email.equals(EMAIL_ADDRESS_PATTERN)) {
        return true
    }
    return false
}

fun isPasswordValid(password: String): Boolean {
    val PASSWORD_PATTERN: Pattern = Pattern.compile(
        //((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})
        "[a-zA-Z0-9].{8,40}"
    )
    if (password.equals(PASSWORD_PATTERN)) {
        return true
    }
    return false
}

fun isTokenExpire(start: String, stop: String): Boolean {

    val MIN_TOKEN_AGE = 2

    val startTime: List<String> = start.split(":")
    val currentTime: List<String> = stop.split(":")

    var h1:Int = currentTime[0].toInt()
    var m1:Int = currentTime[1].toInt()
    var s1:Int = currentTime[2].toInt()
    var h2:Int = startTime[0].toInt()
    var m2:Int = startTime[1].toInt()
    var s2:Int = startTime[2].toInt()

    if (s2 > s1) {
        --m1
        s1 += 60
    }

    val s3 = s1 - s2
    if (m2 > m1) {
        --h1
        m1 += 60
    }

    val m3 = m1 - m2
    val h3 = h1 - h2

    if (h3 >= MIN_TOKEN_AGE) {
        return true
    }

    return false
}