package com.drivingschool.android.ui.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.drivingschool.android.R
import com.drivingschool.android.ui.DashboardActivity
import kotlinx.android.synthetic.main.fragment_two_user.view.*


class TwoUserFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_two_user, container, false)

        view.cardSchool.setOnClickListener {
            val intent = Intent(context,DashboardActivity::class.java)
            startActivity(intent)
        }


        view.cardStudent.setOnClickListener {
            val intent = Intent(context,DashboardActivity::class.java)
            startActivity(intent)
        }


        return view
    }


}
