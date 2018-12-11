package com.drivingschool.android.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.drivingschool.android.R
import kotlinx.android.synthetic.main.school_review_frag.view.*

class SchoolReviewFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.school_review_frag, container, false)

        view.tvSelectNow.setOnClickListener {

        }

        return view
    }
}
