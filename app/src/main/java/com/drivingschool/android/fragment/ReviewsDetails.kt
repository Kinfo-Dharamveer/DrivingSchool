package com.drivingschool.android.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.drivingschool.android.R
import kotlinx.android.synthetic.main.reviews_details.view.*

class ReviewsDetails : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.reviews_details, container, false)

        view.tvReviewBy.setText("")

        return view
    }
}
