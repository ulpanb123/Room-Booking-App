package com.example.jusanbookingapp.constants

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object AppPreferences {
    private var sharedPreferences: SharedPreferences? = null

    // TODO step 1: call `AppPreferences.setup(applicationContext)` in your MainActivity's `onCreate` method

    fun setup(context: Context) {
        // TODO step 2: set your app name here
        sharedPreferences = context.getSharedPreferences("JusanBookingApp.sharedprefs",
            Context.MODE_PRIVATE
        )
    }

    // TODO step 4: replace these example attributes with your stored values
    var accessToken: String?
        get() = Key.ACCESS.getString()
        set(value) = Key.ACCESS.setString(value)

//    var refreshToken: String?
//        get() = Key.REFRESH.getString()
//        set(value) = Key.REFRESH.setString(value)

    var userId : Int?
        get() = Key.USERID.getInt()
        set(value) = Key.USERID.setInt(value)

    var username: String?
        get() = Key.USERNAME.getString()
        set(value) = Key.USERNAME.setString(value)

    var userEmail : String?
        get() = Key.USEREMAIL.getString()
        set(value) = Key.USEREMAIL.setString(value)

    var password : String?
        get() = Key.PASSWORD.getString()
        set(value) = Key.PASSWORD.setString(value)

    private enum class Key {
        ACCESS, USERNAME,USERID, USEREMAIL, PASSWORD; // TODO step 3: replace these cases with your stored values keys

        fun getString(): String? = if (sharedPreferences!!.contains(name)) sharedPreferences!!.getString(name, "") else null

        fun setString(value: String?) = value?.let { sharedPreferences!!.edit { putString(name, value) } } ?: remove()

        fun remove() = sharedPreferences!!.edit { remove(name) }

        fun getInt() : Int? = if (sharedPreferences!!.contains(name)) sharedPreferences!!.getInt(name, -1) else null
        fun setInt(value: Int?) = value?.let { sharedPreferences!!.edit { putInt(name, value) } } ?: remove()

    }
}