package com.hyemin.todolist_login_register.View

import android.app.Activity
import android.app.Activity.*
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hyemin.todolist_login_register.Model.App
import com.hyemin.todolist_login_register.Model.Prefs
import com.hyemin.todolist_login_register.Model.UserModel
import com.hyemin.todolist_login_register.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private var userModel: UserModel? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        App.prefs = Prefs(this)
        auto_checkBox.setChecked(App.prefs.checkBox)
        userModel = UserModel(applicationContext)

        when {
            ((auto_checkBox.isChecked == true)&&(App.prefs.email.length!=0)) -> {
                val email = App.prefs.email
                val pw = App.prefs.passwd

                when {
                    userModel!!.checkLogin(email, pw) -> {
                        Toast.makeText(
                            applicationContext,
                            App.prefs.email+"님 환영합니다.",
                            Toast.LENGTH_LONG
                        ).show()
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        applicationContext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                        this.finish()

                    }

                }
            }
            else->{
                login_email.setText(App.prefs.email)
            }
        }

        login_button.setOnClickListener {
            App.prefs.email = login_email.text.toString()
            App.prefs.passwd = login_pw.text.toString()

            val email = login_email.text.toString()
            val pw = login_pw.text.toString()

            when {
                userModel!!.checkLogin(email, pw) -> {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    applicationContext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                    this.finish()
                }

                else -> {
                    Toast.makeText(
                        applicationContext,
                        "일치하는 계정이 없습니다",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        register_button.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        auto_checkBox.setOnClickListener {
            App.prefs.checkBox = auto_checkBox.isChecked
        }
    }
}
