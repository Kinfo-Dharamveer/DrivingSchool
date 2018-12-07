package com.drivingschool.android.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.drivingschool.android.AppConstants
import com.drivingschool.android.R
import com.drivingschool.android.response.register.RegisterResponse
import com.drivingschool.android.restclient.RestClient
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        ReferenceControl()

    }

    private fun ReferenceControl() {

        btnLogin!!.setOnClickListener {
            ValidateRegister()
        }
    }

    private fun ValidateRegister() {

        when {
            edNameRegister!!.text.toString() == "" -> {
                edNameRegister!!.setError("Enter your name")
                return
            }
            edSchoolNameReg!!.text.toString() == "" -> {
                edSchoolNameReg!!.setError("Enter your School name")
                return
            }
            edContactReg!!.text.toString() == "" -> {
                edContactReg!!.setError("Enter your contact no")
                return
            }
            edEmailRegister!!.text.toString() == "" -> {
                edEmailRegister!!.setError("Enter your email")
                return
            }
            edPswRegister!!.text.toString() == "" -> {
                edPswRegister!!.setError("Enter your psw")
                return
            }
            else -> {

                showpDialog()

                val stringHashMap  = HashMap<String, String>()

                stringHashMap.put("name", edNameRegister!!.text.toString())
                stringHashMap.put("email", edEmailRegister!!.text.toString())
                stringHashMap.put("password", edPswRegister!!.text.toString())
                stringHashMap.put("school_name", edSchoolNameReg!!.text.toString())
                stringHashMap.put("contact", edContactReg!!.text.toString())
                stringHashMap.put("address", edAddress!!.text.toString())

                val restClient = RestClient.getClient()

                restClient.register(stringHashMap).enqueue(object : Callback<RegisterResponse> {

                    override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {

                        if (response.isSuccessful){

                            if (response.body()!!.getStatus()=="Success") {

                                Toast.makeText(applicationContext,"You are successfully registered",Toast.LENGTH_SHORT).show()

                                hidepDialog()
                                val i = Intent(applicationContext, LoginActivity::class.java)
                                startActivity(i)
                                finish()

                            }
                            else{

                                ErrorAlertDialog(response.body()!!.getMessage())
                                hidepDialog()

                            }
                        }
                        else{

                            ErrorAlertDialog(response.message())
                            hidepDialog()
                            edNameRegister!!.text.clear()
                            edSchoolNameReg!!.text.clear()
                            edContactReg!!.text.clear()
                            edEmailRegister!!.text.clear()
                            edPswRegister!!.text.clear()
                        }

                    }

                    override fun onFailure(call: Call<RegisterResponse>?, t: Throwable?) {
                        ErrorAlertDialog(t.toString())
                        hidepDialog()


                    }

                })

            }
        }

    }



}
