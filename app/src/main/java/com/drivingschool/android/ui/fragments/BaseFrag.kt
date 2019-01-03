package com.drivingschool.android.ui.fragments

import android.app.ProgressDialog
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.drivingschool.android.data.Constants
import com.drivingschool.android.service.NetworkChangeReceiver

open class BaseFrag : Fragment(){

    internal var pDialog: ProgressDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //Dialog
        pDialog = ProgressDialog(context)
        pDialog!!.setMessage("Please wait...")
        pDialog!!.setCancelable(false)




        return super.onCreateView(inflater, container, savedInstanceState)
    }


    fun showpDialog() {
        if (!pDialog!!.isShowing())
            pDialog!!.show()
    }

    fun hidepDialog() {
        if (pDialog!!.isShowing())
            pDialog!!.dismiss()
    }


    fun ErrorAlertDialog(msg: String) {
        val builder = AlertDialog.Builder(context!!)

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
