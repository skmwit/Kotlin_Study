package com.hyemin.todolist_login_register.View

import android.content.Intent
import android.view.View
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hyemin.todolist_login_register.Model.UserModel
import com.hyemin.todolist_login_register.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private var userModel: UserModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userModel = UserModel(applicationContext)

        login_button.setOnClickListener{
            val email = login_email.text.toString()
            val pw = login_pw.text.toString()
            val checkLogin = userModel!!.checkLogin(email, pw)

            if (checkLogin) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                applicationContext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                this.finish()
            } else {
                Toast.makeText(applicationContext, "일치하는 계정이 없습니다", Toast.LENGTH_LONG).show()
            }
        }
        register_button.setOnClickListener{
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}
