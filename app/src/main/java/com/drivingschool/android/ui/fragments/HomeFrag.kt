package com.drivingschool.android.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.drivingschool.android.R
import com.drivingschool.android.ui.DashboardActivity

class HomeFrag : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.home_frag,container,false)

        val mainActivity = activity as DashboardActivity
        mainActivity.setToolbarTittle("Dashboard")


        return view
    }
}