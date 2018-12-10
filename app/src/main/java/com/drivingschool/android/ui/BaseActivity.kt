package com.drivingschool.android.ui

import android.app.Activity
import android.os.Bundle
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.drivingschool.android.data.Constants.CONNECTIVITY_ACTION
import com.drivingschool.android.service.NetworkChangeReceiver
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper





open class BaseActivity: AppCompatActivity() {

   internal var pDialog: ProgressDialog? = null
    internal lateinit var intentFilter: IntentFilter
    internal lateinit var receiver: NetworkChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Dialog
        pDialog = ProgressDialog(this)
        pDialog!!.setMessage("Please wait...")
        pDialog!!.setCancelable(false)

        intentFilter = IntentFilter()
        intentFilter.addAction(CONNECTIVITY_ACTION)
        receiver = NetworkChangeReceiver()


    }


    protected override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(receiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }


    fun showpDialog() {
        if (!pDialog!!.isShowing())
            pDialog!!.show()
    }

     fun hidepDialog() {
        if (pDialog!!.isShowing())
            pDialog!!.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        // noInternetDialog!!.onDestroy()
    }

    fun ErrorAlertDialog(msg: String) {
        val builder = AlertDialog.Builder(this)

        // Set the alert dialog title
        builder.setTitle(msg)

        // Display a message on alert dialog
        //  builder.setMessage("Are you want to set the app background color to RED?")

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("OK"){dialog, which ->
            // Do something when user press the positive button
          dialog.dismiss()

        }

        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }


}