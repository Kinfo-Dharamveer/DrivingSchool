package com.drivingschool.android.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.drivingschool.android.AppConstants
import com.drivingschool.android.R
import com.orhanobut.hawk.Hawk
import java.lang.Exception

class SplashActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val timerThread = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(3000)
                    if (Hawk.get(AppConstants.USER_ID, "") == "") {
                        val i = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(i)
                        overridePendingTransition(0,0)
                        finish()
                    } else {
                        val i = Intent(applicationContext, MainActivity::class.java)
                        startActivity(i)
                        overridePendingTransition(0,0)
                        finish()
                    }
                } catch (i: Exception) {
                    i.printStackTrace()
                }
            }
        }
        timerThread.start()
    }
}
