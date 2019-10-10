package com.hyemin.todolist_login_register.Model

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context){

    val PREFS_FILENAME = "prefs"
    val USER_EMAIL = "email"
    val USER_PASSWD = "passwd"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME,0)

    var email: String
        get() = prefs.getString(USER_EMAIL,"")!!
        set(value) = prefs.edit().putString(USER_EMAIL, value).apply()

    var passwd: String
        get()=prefs.getString(USER_PASSWD,"")!!
        set(value) = prefs.edit().putString(USER_PASSWD, value).apply()

    var checkBox: Boolean
        get() = prefs.getBoolean("checkBox", false)
        set(value) = prefs.edit().putBoolean("checkBox", value).apply()

}