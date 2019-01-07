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
import com.drivingschool.android.DialogUtils



class RegisterFrag : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.register_frag, container, false)

        view.btnLoginRegister.setOnClickListener {

            when {
                view.edNameRegister!!.text.toString() == "" -> {
                    view.edNameRegister!!.setError("Enter your name")

                }
                view.edEmailRegister!!.text.toString() == "" -> {
                    view.edEmailRegister!!.setError("Enter your email")

                }
                view.edPswRegister!!.text.toString() == "" -> {
                    view.edPswRegister!!.setError("Enter your psw")
                }
                view.edConfirmPassword!!.text.toString() == "" -> {
                    view.edPswRegister!!.setError("Enter confirm psw")
                }

                else -> {

                    val myDialog = DialogUtils.showProgressDialog(context, "Registering......")

                    val stringHashMap  = HashMap<String, String>()

                    stringHashMap.put("name", edNameRegister!!.text.toString())
                    stringHashMap.put("email", edEmailRegister!!.text.toString())
                    stringHashMap.put("password", edPswRegister!!.text.toString())
                    stringHashMap.put("password_confirmation", edConfirmPassword!!.text.toString())

                    val restClient = RestClient.getClient()

                    restClient.register(stringHashMap).enqueue(object : Callback<RegisterResponse> {

                        override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {

                            if (response.isSuccessful){

                                if (response.body()!!.getStatus()=="Success") {

                                    myDialog.dismiss()

                                    Toast.makeText(context,"You are successfully registered", Toast.LENGTH_SHORT).show()

                                    val f1 = TwoUserFragment()
                                    val fragmentTransaction = fragmentManager!!.beginTransaction()
                                    fragmentTransaction.replace(R.id.home_container, f1)
                                    fragmentTransaction.commit()

                                }
                                else{

                                    Toast.makeText(context,response.body()!!.getMessage(), Toast.LENGTH_SHORT).show()
                                    myDialog.dismiss()

                                }
                            }
                            else{

                                Toast.makeText(context,response.body()!!.getMessage(), Toast.LENGTH_SHORT).show()
                                myDialog.dismiss()
                                view.edNameRegister!!.text.clear()
                                view.edEmailRegister!!.text.clear()
                                view.edPswRegister!!.text.clear()
                                view.edConfirmPassword!!.text.clear()
                            }

                        }

                        override fun onFailure(call: Call<RegisterResponse>?, t: Throwable?) {
                            Toast.makeText(context,t.toString(), Toast.LENGTH_SHORT).show()
                            myDialog.dismiss()


                        }

                    })

                }
            }

        }

        return view


    }


}
