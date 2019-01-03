package com.drivingschool.android.ui

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import com.drivingschool.android.R
import com.drivingschool.android.customviews.CustomTextView
import com.drivingschool.android.data.MessageEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class ForgotPasswordActivity : BaseActivity() {

    private var edEmailRs: EditText? = null
    private var txtResetPsw: CustomTextView? = null
    private var txtSignUp: CustomTextView? = null


    internal lateinit var notInternetLayout: LinearLayout
    internal lateinit var main_layout: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        ReferenceControl()
    }

    @Subscribe
    fun onEvent(status: MessageEvent){

        if (status.status.contains("NOT_CONNECT")){


            notInternetLayout.visibility = View.VISIBLE
            main_layout.setVisibility(View.GONE)
           // Toast.makeText(this,"NOT CONNECTED", Toast.LENGTH_SHORT).show()

        }
        else{
            main_layout.setVisibility(View.VISIBLE)
            notInternetLayout.visibility = View.GONE
           // Toast.makeText(this,"CONNECTED", Toast.LENGTH_SHORT).show()

        }

    }



    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }


    private fun ReferenceControl() {

        main_layout = findViewById(R.id.main_layout)
        notInternetLayout = findViewById(R.id.notInternetLayout)

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
