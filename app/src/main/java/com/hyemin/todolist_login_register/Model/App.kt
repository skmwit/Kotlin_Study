package com.hyemin.todolist_login_register.Model

import android.app.Application

class App : Application(){
    companion object {
        lateinit var prefs: Prefs
    }

    override fun onCreate() {

        prefs = Prefs(applicationContext)
        super.onCreate()
    }
}
