package com.hyemin.todolist_login_register.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.hyemin.todolist_login_register.Model.UserModel
import com.hyemin.todolist_login_register.R

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private var userModel: UserModel? = null

    private var registerBtn: TextView? = null

    private var emailEdit: EditText? = null
    private var pwEdit: EditText? = null
    private var pwCheckEdit: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        userModel = UserModel(applicationContext)

        registerBtn = findViewById<View>(R.id.register_register_button) as TextView
        emailEdit = findViewById<View>(R.id.register_email) as EditText
        pwEdit = findViewById<View>(R.id.register_pw) as EditText
        pwCheckEdit = findViewById<View>(R.id.register_pwCheck) as EditText

        registerBtn!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.register_register_button -> {
                val email = emailEdit!!.text.toString()
                val pw = pwEdit!!.text.toString()
                val pwCheck = pwCheckEdit!!.text.toString()
                when (userModel!!.signUp(email, pw, pwCheck)) {
                    "Success" -> {
                        // 자동 로그인 처리 필요

                        val intent = Intent(applicationContext, LoginActivity::class.java)
                        applicationContext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                        finish()
                        // 테스트를 위해 LoginActivity 로 이동. 추후 MainActivity 로 변경 예정
                    }
                    "NotChecked" -> Toast.makeText(applicationContext, "비밀번호와 비밀번호 확인이 일치하지 않습니다", Toast.LENGTH_LONG).show()
                    else -> Toast.makeText(applicationContext, "동일한 이메일의 계정이 존재합니다", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
