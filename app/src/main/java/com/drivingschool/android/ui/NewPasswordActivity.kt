package com.drivingschool.android.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.drivingschool.android.R
import com.drivingschool.android.customviews.CustomTextView
import com.drivingschool.android.data.MessageEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class NewPasswordActivity : BaseActivity() {

    private var edNewPass: EditText? = null
    private var edConfirmPass: EditText? = null
    private var btnSubmitNewPass: CustomTextView? = null

    internal lateinit var notInternetLayout: LinearLayout
    internal lateinit var main_layout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_password)

        main_layout = findViewById(R.id.main_lay)
        notInternetLayout = findViewById(R.id.notInternetLayout)


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


    @Subscribe
    fun onEvent(status: MessageEvent){

        if (status.status.contains("NOT_CONNECT")){

            notInternetLayout.visibility = View.VISIBLE
            main_layout.setVisibility(View.GONE)
         //   Toast.makeText(this,"NOT CONNECTED", Toast.LENGTH_SHORT).show()

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

}
