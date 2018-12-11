package com.drivingschool.android.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.drivingschool.android.R
import com.drivingschool.android.adapters.SchoolsAdapter
import com.drivingschool.android.customviews.SimpleDividerItemDecoration
import com.drivingschool.android.response.schooldata.SchoolPayload
import com.drivingschool.android.response.schooldata.SchoolSuccess
import com.drivingschool.android.restclient.RestClient
import kotlinx.android.synthetic.main.schools_layout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SchoolsFrag : Fragment(),SchoolsAdapter.buttonClickListenter {


    private lateinit var schoolAdapter: SchoolsAdapter
    private lateinit var layoutmanager: RecyclerView.LayoutManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.schools_layout, container, false)

        view.recyclerview_schools.setHasFixedSize(true)
        layoutmanager = LinearLayoutManager(context)
        view.recyclerview_schools.layoutManager = layoutmanager
        view.recyclerview_schools.itemAnimator = DefaultItemAnimator()
        view.recyclerview_schools.addItemDecoration(SimpleDividerItemDecoration(context))


        val restClient = RestClient.getClient()

        restClient.schoolList().enqueue(object : Callback<SchoolSuccess>{

            override fun onResponse(call: Call<SchoolSuccess>, response: Response<SchoolSuccess>) {

                if (response.isSuccessful){

                    Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()

                    val schoolArrayList = response.body()!!.schools

                    schoolAdapter = SchoolsAdapter(schoolArrayList, context,this@SchoolsFrag)
                    view.recyclerview_schools.adapter = schoolAdapter
                }
                else{
                    Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()

                }

            }
            override fun onFailure(call: Call<SchoolSuccess>, t: Throwable) {

                Toast.makeText(context,t.toString(),Toast.LENGTH_SHORT).show()

            }
        })

        return view
    }

    override fun reviewClick(pos: Int) {

        val f1 = SchoolReviewFragment()
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.home_container, f1)
        fragmentTransaction.commit()

    }

    override fun readMoreClick(pos: Int) {

        val f1 = SchoolReviewFragment()
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.home_container, f1)
        fragmentTransaction.commit()

    }



}
