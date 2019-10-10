package com.hyemin.todolist_login_register.View

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hyemin.todolist_login_register.Model.App
import com.hyemin.todolist_login_register.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logout.setOnClickListener{
            val intent = Intent(applicationContext, LoginActivity::class.java)
            applicationContext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            finish()
            App.prefs.checkBox = false
            App.prefs.email = ""
            App.prefs.passwd = ""
        }
    }
}
