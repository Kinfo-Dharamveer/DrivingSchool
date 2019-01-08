package com.drivingschool.android.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.drivingschool.android.R
import com.drivingschool.android.adapters.InstructorsAdapter
import com.drivingschool.android.customviews.CustomTextView
import com.drivingschool.android.customviews.SimpleDividerItemDecoration
import com.drivingschool.android.response.instructorList.InstructorData
import com.drivingschool.android.restclient.RestClient
import com.drivingschool.android.ui.DashboardActivity
import kotlinx.android.synthetic.main.layout_instructor.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class InstructorFrag : Fragment(), InstructorsAdapter.mClickListener {


    private lateinit var viewAdapter: InstructorsAdapter
    private lateinit var layoutmanager: RecyclerView.LayoutManager
    private lateinit var alertDialog : AlertDialog


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.layout_instructor, container, false)

        val mainActivity = activity as DashboardActivity
        mainActivity.setToolbarTittle("Instructor")


        view.recycler_inst.setHasFixedSize(true)
        layoutmanager = LinearLayoutManager(context)
        view.recycler_inst.layoutManager = layoutmanager
        view.recycler_inst.itemAnimator = DefaultItemAnimator()
        view.recycler_inst.addItemDecoration(SimpleDividerItemDecoration(context))

        val restClient = RestClient.getClient()

        restClient.instructorsList().enqueue(object : Callback<InstructorData>{

            override fun onResponse(call: Call<InstructorData>, response: Response<InstructorData>) {


                if (response.isSuccessful){

                    Toast.makeText(context,"Success", Toast.LENGTH_SHORT).show()

                    val instructorList = response.body()!!.instructors

                    viewAdapter = InstructorsAdapter(instructorList, context, this@InstructorFrag)

                    view.recycler_inst.adapter = viewAdapter

                }
                else{
                    Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()

                }

            }

            override fun onFailure(call: Call<InstructorData>, t: Throwable) {

            }



        })



        view.addNewInstr.setOnClickListener {

            val dialogBuilder = AlertDialog.Builder(context!!)
            // ...Irrelevant code for customizing the buttons and title
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.add_new_instr, null)
            dialogBuilder.setView(dialogView)

            val submitInst = dialogView.findViewById(R.id.submitInst) as CustomTextView


            alertDialog = dialogBuilder.create()
            alertDialog.show()


            submitInst.setOnClickListener {

                alertDialog.dismiss()
            }


        }

      //  instructorData()

        return view
    }



    override fun mClick(v: View?, position: Int) {


        val someFragment = InstructorDetailFrag()
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.main_container, someFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }


}
