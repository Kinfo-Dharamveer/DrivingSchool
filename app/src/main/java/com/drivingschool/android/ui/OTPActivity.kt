package com.drivingschool.android.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.EditText
import android.widget.Toast
import com.drivingschool.android.R
import com.drivingschool.android.customviews.CustomTextView

class OTPActivity : BaseActivity() {

    private var edOTP: EditText? = null
    private var btnSubmitOTP: CustomTextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        edOTP = findViewById(R.id.edOTP)
        btnSubmitOTP = findViewById(R.id.btnSubmitOTP)


        btnSubmitOTP!!.setOnClickListener {

            when {
                edOTP!!.text.toString() == "" ->{
                    edOTP!!.setError("Enter OTP")
                }
                else ->{

                    val builder = AlertDialog.Builder(this)

                    // Set the alert dialog title
                    builder.setTitle("Password has changed successfully")

                    // Display a message on alert dialog
                  //  builder.setMessage("Are you want to set the app background color to RED?")

                    // Set a positive button and its click listener on alert dialog
                    builder.setPositiveButton("YES"){dialog, which ->
                        // Do something when user press the positive button
                        val i = Intent(applicationContext,LoginActivity::class.java)
                        startActivity(i)
                    }

                    // Finally, make the alert dialog using builder
                    val dialog: AlertDialog = builder.create()

                    // Display the alert dialog on app interface
                    dialog.show()

                }
            }
        }
    }
}
