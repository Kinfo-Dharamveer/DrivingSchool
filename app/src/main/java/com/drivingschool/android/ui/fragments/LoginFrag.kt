package com.drivingschool.android.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.drivingschool.android.AppConstants
import com.drivingschool.android.DialogUtils
import com.drivingschool.android.R
import com.drivingschool.android.ui.DashboardActivity
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.login_frag.view.*
import com.androidnetworking.error.ANError
import org.json.JSONObject
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority






class LoginFrag : Fragment(){


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

                    val myDialog = DialogUtils.showProgressDialog(context, "Progressing......")


                    AndroidNetworking.post(resources.getString(R.string.api_url)+"login")
                            .addBodyParameter("email", view.edEmail!!.text.toString())
                            .addBodyParameter("password", view.edPsw!!.text.toString())
                            .setTag("test")
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(object : JSONObjectRequestListener {
                                override fun onResponse(response: JSONObject) {
                                    // do anything with response
                                    myDialog.dismiss()

                                    val responseString = response.toString()
                                    val jsonData = JSONObject(responseString)

                                    if (jsonData.getString("status").equals("Success")){

                                        Toast.makeText(context, jsonData.getString("message").toString(), Toast.LENGTH_SHORT).show()

                                       // val jsonUser  = jsonData.getString("user").length

                                        val jsonUser  = jsonData.getJSONObject("user")

                                        for (i in 0 until jsonUser.length()) {

                                            Hawk.put(AppConstants.USER_ID, jsonUser.getString("id"))
                                            Hawk.put(AppConstants.USER_NAME, jsonUser.getString("name"))
                                            Hawk.put(AppConstants.USER_EMAIL, jsonUser.getString("email"))
                                            Hawk.put(AppConstants.ROLE_ID, jsonUser.getInt("role"))


                                            if (jsonUser.getInt("role").equals(0)){

                                                val f1 = TwoUserFragment()
                                                val fragmentTransaction = fragmentManager!!.beginTransaction()
                                                fragmentTransaction.replace(R.id.home_container, f1)
                                                fragmentTransaction.commit()
                                            }
                                            else if (jsonUser.getInt("role").equals(1)){
                                                //Open School
                                                val i1 = Intent(context, DashboardActivity::class.java)
                                                startActivity(i1)
                                            }
                                            else{

                                                //Open Student
                                                val i = Intent(context, DashboardActivity::class.java)
                                                startActivity(i)
                                            }
                                        }

                                    }
                                    else{
                                        myDialog.dismiss()
                                        Toast.makeText(context, jsonData.getString("message").toString(), Toast.LENGTH_SHORT).show()

                                    }




                                }

                                override fun onError(error: ANError) {
                                    myDialog.dismiss()

                                    // handle error
                                    Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()

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
