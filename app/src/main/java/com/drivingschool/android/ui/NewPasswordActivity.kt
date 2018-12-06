package com.drivingschool.android.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.drivingschool.android.R
import com.drivingschool.android.customviews.CustomTextView

class NewPasswordActivity : BaseActivity() {

    private var edNewPass: EditText? = null
    private var edConfirmPass: EditText? = null
    private var btnSubmitNewPass: CustomTextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_password)

        edNewPass = findViewById(R.id.edNewPass)
        edConfirmPass = findViewById(R.id.edConfirmPass)
        btnSubmitNewPass = findViewById(R.id.btnSubmitNewPass)

        btnSubmitNewPass!!.setOnClickListener {

            when{
                edNewPass!!.text.toString() == "" ->{
                    edNewPass!!.setError("Enter new password")

                }
                edConfirmPass!!.text.toString() == "" ->{
                    edConfirmPass!!.setError("Enter confirm password")
                }
                else ->{
                    val i = Intent(applicationContext,OTPActivity::class.java)
                    startActivity(i)
                }
            }
        }

    }
}
