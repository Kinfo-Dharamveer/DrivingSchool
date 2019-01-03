package com.drivingschool.android.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView

import com.drivingschool.android.R
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.first_home_frag.view.*
import com.androidnetworking.error.ANError
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.interfaces.JSONObjectRequestListener
import org.json.JSONObject


class FirstHomeFrag : Fragment(), AdapterView.OnItemSelectedListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.first_home_frag, container, false)

        // Spinner Drop down elements
        val categories = ArrayList<String>()

        AndroidNetworking.get("http://112.196.85.181:9083/drive/public/api_search/get_schools?q=my")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {

                        val responseString = response.toString();
                        val jsonData = JSONObject(responseString)

                        val schoolsArray = jsonData.getJSONArray("schools")

                        for (i in 0 until schoolsArray.length()) {


                            val jObj = schoolsArray.getJSONObject(i)

                            Toast.makeText(context, jObj.getString("name").toString(), Toast.LENGTH_LONG).show();
                            categories.add(jObj.getString("name").toString())
                            categories.add(jObj.getString("country").toString())

                        }

                    }

                    override fun onError(anError: ANError?) {

                        Toast.makeText(context,anError.toString(),Toast.LENGTH_SHORT).show()
                    }

                })


        // Creating adapter for spinner
        val dataAdapter = ArrayAdapter<String>(context, R.layout.spinner_item, categories)

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner

        //Creating the instance of ArrayAdapter containing list of fruit names
        val adapter = ArrayAdapter<String>(context, android.R.layout.select_dialog_item, categories)
        //Getting the instance of AutoCompleteTextView
        view.autoCompleteTextView.threshold = 1//will start working from first character
        view.autoCompleteTextView.setAdapter<ArrayAdapter<String>>(adapter)//setting the adapter data into the AutoCompleteTextView
        view.autoCompleteTextView.setTextColor(Color.RED)


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
