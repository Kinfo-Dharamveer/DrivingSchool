package com.drivingschool.android.ui

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.drivingschool.android.R
import com.drivingschool.android.customviews.CustomTextView

class ForgotPasswordActivity : BaseActivity() {

    private var edEmailRs: EditText? = null
    private var txtResetPsw: CustomTextView? = null
    private var txtSignUp: CustomTextView? = null
    private lateinit var mSPF: SharedPreferences
    internal lateinit var mEDT: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        ReferenceControl()
    }

    private fun ReferenceControl() {
        mSPF = getSharedPreferences("AppData", 0)
        mEDT = mSPF.edit()
        edEmailRs = findViewById(R.id.edEmailRs)
        txtResetPsw = findViewById(R.id.txtResetPsw)
        txtSignUp = findViewById(R.id.txtSignUp)

        txtResetPsw!!.setOnClickListener {

            when {

                edEmailRs!!.text.toString() == "" ->{
                    edEmailRs!!.setError("Enter your email")

                }

                else ->{
                    val i = Intent(applicationContext, NewPasswordActivity::class.java)
                    startActivity(i)
                }
            }
        }
        txtSignUp!!.setOnClickListener {
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
