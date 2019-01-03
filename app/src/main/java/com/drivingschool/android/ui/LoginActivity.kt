package com.drivingschool.android.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import com.drivingschool.android.AppConstants
import com.drivingschool.android.R
import com.drivingschool.android.data.MessageEvent
import com.drivingschool.android.ui.fragments.FirstHomeFrag
import com.drivingschool.android.ui.fragments.SchoolsFrag
import com.drivingschool.android.response.login.LoginResponse
import com.drivingschool.android.restclient.RestClient
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_login.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ReferenceControl()

    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private fun ReferenceControl() {

        txtHomeLogin.setOnClickListener {
            val f1 = FirstHomeFrag()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.home_containerLogin, f1)
            fragmentTransaction.commit()
        }

        txtSchoolsLogin.setOnClickListener {
            val f1 = SchoolsFrag()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.home_containerLogin, f1)
            fragmentTransaction.commit()
        }

        txtLoginLogin.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))

        }

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

                                Toast.makeText(applicationContext,"You are successfully login",Toast.LENGTH_SHORT).show()

                                Hawk.put(AppConstants.USER_ID, response.body()!!.getUserId().toString())

                                val i = Intent(applicationContext, MainActivity::class.java)
                                startActivity(i)
                                overridePendingTransition(0,0)

                                finish()

                            }
                            else
                            {
                                ErrorAlertDialog(response.body()!!.getMessage()!!)
                            }

                        }
                        else
                        {
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

    @Subscribe
    fun onEvent(status: MessageEvent){

        if (status.status.contains("NOT_CONNECT")){

            notInternetLayoutLogin.visibility = View.VISIBLE
            home_containerLogin.setVisibility(View.GONE)
           // Toast.makeText(this,"NOT CONNECTED", Toast.LENGTH_SHORT).show()

        }
        else{
            home_containerLogin.setVisibility(View.VISIBLE)
            notInternetLayoutLogin.visibility = View.GONE
           // Toast.makeText(this,"CONNECTED", Toast.LENGTH_SHORT).show()

        }

    }


}
