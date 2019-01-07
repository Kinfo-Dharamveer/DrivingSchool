package com.drivingschool.android.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.drivingschool.android.R
import kotlinx.android.synthetic.main.activity_otp.view.*

class OtpFrag : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.activity_otp, container, false)

        view.btnSubmitOTP!!.setOnClickListener {

            when {
                view.edOTP!!.text.toString() == "" ->{
                    view.edOTP!!.setError("Enter OTP")
                }
                else ->{

                    val builder = AlertDialog.Builder(context!!)

                    // Set the alert dialog title
                    builder.setTitle("Password has changed successfully")

                    // Display a message on alert dialog
                    //  builder.setMessage("Are you want to set the app background color to RED?")

                    // Set a positive button and its click listener on alert dialog
                    builder.setPositiveButton("YES"){dialog, which ->
                        // Do something when user press the positive button
                        val f1 = LoginFrag()
                        val fragmentTransaction = fragmentManager!!.beginTransaction()
                        fragmentTransaction.replace(R.id.home_containerLogin, f1)
                        fragmentTransaction.commit()
                    }

                    // Finally, make the alert dialog using builder
                    val dialog: AlertDialog = builder.create()

                    // Display the alert dialog on app interface
                    dialog.show()

                }
            }
        }


        return view
    }
}
