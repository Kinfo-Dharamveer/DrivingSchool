package com.drivingschool.android.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener

import com.drivingschool.android.R
import com.drivingschool.android.response.feedback.FeedbackSuccess
import com.drivingschool.android.restclient.RestClient
import kotlinx.android.synthetic.main.about_us_frag.*
import kotlinx.android.synthetic.main.about_us_frag.view.*
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AboutUs : Fragment() {





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.about_us_frag, container, false)


        view.btnSendFeedback.setOnClickListener {

            val restClient = RestClient.getClient();

            val stringHashMap = HashMap<String,String>()

            stringHashMap.put("email",edEmailFeedback!!.text.toString())
            stringHashMap.put("name",edNameFeedback!!.text.toString())
            stringHashMap.put("subject",edSubjectFeedback!!.text.toString())
            stringHashMap.put("feedback",edFeedback!!.text.toString())

            restClient.sendFeedback(stringHashMap).enqueue(object : Callback<FeedbackSuccess> {

                override fun onResponse(call: Call<FeedbackSuccess>, response: Response<FeedbackSuccess>) {

                    if (response.isSuccessful){

                        Toast.makeText(context,response.body()!!.success,Toast.LENGTH_SHORT).show()

                    }
                    else{
                        Toast.makeText(context,"false",Toast.LENGTH_SHORT).show()

                    }



                }

                override fun onFailure(call: Call<FeedbackSuccess>, t: Throwable) {

                    Toast.makeText(context,t.toString(),Toast.LENGTH_SHORT).show()

                }




            })
        }



        return view



    }
}
