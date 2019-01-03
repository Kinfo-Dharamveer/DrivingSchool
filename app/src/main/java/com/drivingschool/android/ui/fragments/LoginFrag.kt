package com.drivingschool.android.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.drivingschool.android.AppConstants
import com.drivingschool.android.R
import com.drivingschool.android.response.login.LoginResponse
import com.drivingschool.android.restclient.RestClient
import com.drivingschool.android.ui.MainActivity
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.login_frag.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFrag : BaseFrag(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

       val view = inflater.inflate(R.layout.login_frag, container, false)


        view.btnLogin!!.setOnClickListener {

            when {

                view.edEmail!!.text.toString() == "" -> {
                    view.edEmail!!.setError("Enter your email")

                }
                view.edPsw!!.text.toString() == "" -> {
                    view.edPsw!!.setError("Enter your password")

                }
                else -> {

                    showpDialog()

                    val stringStringHashMap = HashMap<String, String>()
                    stringStringHashMap.put("email",view.edEmail!!.text.toString())
                    stringStringHashMap.put("password",view.edPsw!!.text.toString())

                    val restClient = RestClient.getClient()

                    restClient.login(stringStringHashMap).enqueue(object : Callback<LoginResponse> {

                        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                            if (response.isSuccessful){

                                if (response.body()!!.getStatus().equals("Success")) {

                                    hidepDialog()

                                    Toast.makeText(context,"You are successfully login", Toast.LENGTH_SHORT).show()

                                    Hawk.put(AppConstants.USER_ID, response.body()!!.getUserId().toString())

                                    val i = Intent(context, MainActivity::class.java)
                                    startActivity(i)

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
                                view.edEmail!!.text.clear()
                                view.edPsw!!.text.clear()
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

        view.txtSignUp!!.setOnClickListener {
            val f1 = RegisterFrag()
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.home_container, f1)
            fragmentTransaction.commit()

         //   val i = Intent(context, RegisterActivity::class.java)
          //  startActivity(i)

        }

        view.txtForgotPsw!!.setOnClickListener {
            val f1 = ForgotPasswordFrag()
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.home_container, f1)
            fragmentTransaction.commit()


        }


        return view


    }



}
