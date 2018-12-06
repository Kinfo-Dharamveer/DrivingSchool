package com.drivingschool.android.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.drivingschool.android.AppConstants
import com.drivingschool.android.R
import com.drivingschool.android.response.login.LoginResponse
import com.drivingschool.android.restclient.RestClient
import com.orhanobut.hawk.Hawk
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity() {

    private var btnLogin: Button? = null
    private var edEmail: EditText? = null
    private var edPsw: EditText? = null
    private var txtForgotPsw: TextView? = null
    private var txtSignUp: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ReferenceControl()

    }


    private fun ReferenceControl() {

        btnLogin = findViewById(R.id.btnLogin)
        edEmail = findViewById(R.id.edEmail)
        edPsw = findViewById(R.id.edPsw)
        txtForgotPsw = findViewById(R.id.txtForgotPsw)
        txtSignUp = findViewById(R.id.txtSignUp)


        btnLogin!!.setOnClickListener {
            ValidateLogin()
        }

        txtSignUp!!.setOnClickListener {
            val i = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(i)
            overridePendingTransition(0,0)

        }

        txtForgotPsw!!.setOnClickListener {
            val i = Intent(applicationContext, ForgotPasswordActivity::class.java)
            startActivity(i)
            overridePendingTransition(0,0)

        }


    }

    private fun ValidateLogin() {

        when {

            edEmail!!.text.toString() == "" -> {
                edEmail!!.setError("Enter your email")
                return
            }
            edPsw!!.text.toString() == "" -> {
                edPsw!!.setError("Enter your password")
                return
            }
            else -> {

                showpDialog()

                val stringStringHashMap = HashMap<String, String>()
                stringStringHashMap.put("email",edEmail!!.text.toString())
                stringStringHashMap.put("password",edPsw!!.text.toString())

                  val restClient = RestClient.getClient()

                   restClient.login(stringStringHashMap).enqueue(object : Callback<LoginResponse> {

                       override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                           if (response.isSuccessful){

                               if (response.body()!!.getStatus().equals("Success")) {

                                   hidepDialog()

                                   Hawk.put(AppConstants.USER_ID, response.body()!!.getUserId().toString())

                                   val i = Intent(applicationContext, MainActivity::class.java)
                                   startActivity(i)
                                   overridePendingTransition(0,0)

                                   finish()

                               }
                               else{

                                   ErrorAlertDialog(response.message())

                               }

                           }
                           else{
                               ErrorAlertDialog("Email or password is incorrect")
                               hidepDialog()
                               edEmail!!.text.clear()
                               edPsw!!.text.clear()
                           }

                       }

                       override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {

                           ErrorAlertDialog(t.toString())
                           hidepDialog()

                       }
                   })

            }
        }
    }



}