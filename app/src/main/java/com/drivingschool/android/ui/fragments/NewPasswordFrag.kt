package com.drivingschool.android.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.drivingschool.android.R
import com.drivingschool.android.ui.OTPActivity
import kotlinx.android.synthetic.main.new_password_frag.view.*

class NewPasswordFrag : BaseFrag() {


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
                    val i = Intent(context, OTPActivity::class.java)
                    startActivity(i)
                }
            }
        }

        return view
    }
}
