package com.drivingschool.android.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import com.drivingschool.android.R
import com.drivingschool.android.customviews.CustomTextView

class InstructorDetailFrag : Fragment() {

    private var tvEdit: CustomTextView? = null
    private var edName: EditText? = null
    private var edLanguage: EditText? = null
    private var edVehicle: EditText? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.inst_detail_frag, container, false)

        tvEdit = view.findViewById(R.id.tvEdit)

        edName = view.findViewById(R.id.edName)
        edLanguage = view.findViewById(R.id.edLanguage)
        edVehicle = view.findViewById(R.id.edVehicle)

        tvEdit!!.setOnClickListener {
            edName!!.setBackgroundColor(resources.getColor(R.color.white))
            edLanguage!!.setBackgroundColor(resources.getColor(R.color.white))
            edVehicle!!.setBackgroundColor(resources.getColor(R.color.white))
        }

        return view


    }
}
