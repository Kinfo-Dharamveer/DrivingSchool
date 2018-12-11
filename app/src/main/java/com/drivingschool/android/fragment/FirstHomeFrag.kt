package com.drivingschool.android.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView

import com.drivingschool.android.R
import kotlinx.android.synthetic.main.first_home_frag.view.*
import android.widget.ArrayAdapter
import android.widget.Toast


class FirstHomeFrag : Fragment(), AdapterView.OnItemSelectedListener {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.first_home_frag, container, false)


        // Spinner click listener
        view.spinner.setOnItemSelectedListener(this)


        // Spinner Drop down elements
        val categories = ArrayList<String>()
        categories.add("All Vehicles types")
        categories.add("LMV (Automatic")
        categories.add("HMV (Manual/Auto")

        // Creating adapter for spinner
        val dataAdapter = ArrayAdapter<String>(context, R.layout.spinner_item, categories)

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner
        view.spinner.setAdapter(dataAdapter);


        return view
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
        // On selecting a spinner item
        val item = p0.getItemAtPosition(p2).toString()

        // Showing selected spinner item
        Toast.makeText(context, "Selected: " + item, Toast.LENGTH_LONG).show();

    }




}
