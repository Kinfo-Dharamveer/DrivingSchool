package com.drivingschool.android.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.drivingschool.android.R
import kotlinx.android.synthetic.main.forgot_password_frag.view.*

class ForgotPasswordFrag : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.forgot_password_frag, container, false)

        view.txtResetPsw!!.setOnClickListener {

            when {

                view.edEmailRs!!.text.toString() == "" ->{
                    view.edEmailRs!!.setError("Enter your email")

                }

                else ->{

                    val f1 = NewPasswordFrag()
                    val fragmentTransaction = fragmentManager!!.beginTransaction()
                    fragmentTransaction.replace(R.id.home_container, f1)
                    fragmentTransaction.commit()

                }
            }
        }

        view.txtSignUp!!.setOnClickListener {
              val f1 = RegisterFrag()
              val fragmentTransaction = fragmentManager!!.beginTransaction()
              fragmentTransaction.replace(R.id.home_container, f1)
              fragmentTransaction.commit()

        }




        return view
    }
}
