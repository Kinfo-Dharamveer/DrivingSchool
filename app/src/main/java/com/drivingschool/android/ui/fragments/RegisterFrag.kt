package com.drivingschool.android.ui.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.drivingschool.android.R
import com.drivingschool.android.response.register.RegisterResponse
import com.drivingschool.android.restclient.RestClient
import kotlinx.android.synthetic.main.register_frag.*
import kotlinx.android.synthetic.main.register_frag.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFrag : BaseFrag() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.register_frag, container, false)

        view.btnLoginRegister.setOnClickListener {

            when {
                view.edNameRegister!!.text.toString() == "" -> {
                    view.edNameRegister!!.setError("Enter your name")

                }
                view.edSchoolNameReg!!.text.toString() == "" -> {
                    view.edSchoolNameReg!!.setError("Enter your SchoolPayload name")

                }
                view.edContactReg!!.text.toString() == "" -> {
                    view.edContactReg!!.setError("Enter your contact no")

                }
                view.edEmailRegister!!.text.toString() == "" -> {
                    view.edEmailRegister!!.setError("Enter your email")

                }
                view.edPswRegister!!.text.toString() == "" -> {
                    view.edPswRegister!!.setError("Enter your psw")

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

                                    Toast.makeText(context,"You are successfully registered", Toast.LENGTH_SHORT).show()

                                    hidepDialog()
                                    val f1 = LoginFrag()
                                    val fragmentTransaction = fragmentManager!!.beginTransaction()
                                    fragmentTransaction.replace(R.id.home_containerLogin, f1)
                                    fragmentTransaction.commit()

                                }
                                else{

                                    ErrorAlertDialog(response.body()!!.getMessage()!!)
                                    hidepDialog()



                                }
                            }
                            else{

                                ErrorAlertDialog(response.message())
                                hidepDialog()
                                view.edNameRegister!!.text.clear()
                                view.edSchoolNameReg!!.text.clear()
                                view.edContactReg!!.text.clear()
                                view.edEmailRegister!!.text.clear()
                                view.edPswRegister!!.text.clear()
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

        return view


    }


}
