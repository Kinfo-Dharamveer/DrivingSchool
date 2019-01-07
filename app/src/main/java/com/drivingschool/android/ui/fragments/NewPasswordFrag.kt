package com.drivingschool.android.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.drivingschool.android.R
import kotlinx.android.synthetic.main.new_password_frag.view.*

class NewPasswordFrag : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.new_password_frag, container, false)


        view.btnSubmitNewPass!!.setOnClickListener {

            when{
                view.edNewPass!!.text.toString() == "" ->{
                    view.edNewPass!!.setError("Enter new password")

                }
                view.edConfirmPass!!.text.toString() == "" ->{
                    view.edConfirmPass!!.setError("Enter confirm password")
                }
                else ->{


                    val f1 = OtpFrag()
                    val fragmentTransaction = fragmentManager!!.beginTransaction()
                    fragmentTransaction.replace(R.id.home_container, f1)
                    fragmentTransaction.commit()

                }
            }
        }

        return view
    }
}
