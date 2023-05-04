package com.example.automatedtesting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.automatedtesting.R


class MainActivity : AppCompatActivity() {

    var edtUserName : EditText? = null

    var edtPassWord : EditText? = null

    var btnLogin : Button? = null

    var loginLogic : LoginLogic = LoginLogic()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtUserName = findViewById(R.id.edtUsename)
        edtPassWord = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnLogin?.setOnClickListener {
            var success = loginLogic.login(this,edtUserName?.text.toString(),edtPassWord?.text.toString())
            if (success){
                var intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }


    class LoginLogic {
        fun login(context: Context?, username: String, password: String): Boolean {
            return if (!isUserNameValid(username) || !isPasswordValid(password)) {
                false
            } else {
                //通过服务器判断账户及密码的有效性
                val result = checkFromServer(username, password)
                if (result) {
                    //登录成功保持本地的信息
                    Log.e("TAG","登录成功保持本地的信息")
                   // SharedPreferencesUtils.put(context, username, password)
                }
                result
            }
        }

        private fun isUserNameValid(username: String?): Boolean {
            if (username == null) {
                return false
            }
            return if (username.contains("@")) {
                Patterns.EMAIL_ADDRESS.matcher(username).matches()
            } else {
                !username.trim { it <= ' ' }.isEmpty()
            }
        }

        fun isPasswordValid(password: String?): Boolean {
            return password != null && password.trim { it <= ' ' }.length > 5
        }

        companion object {
            // 为了进行演示，去除通过服务器鉴定的逻辑，当用户输入特定账号及密码为时则验证成功
            private fun checkFromServer(username: String, password: String): Boolean {
                return username == "123@163.com" && password == "123456"
            }
        }
    }
}
