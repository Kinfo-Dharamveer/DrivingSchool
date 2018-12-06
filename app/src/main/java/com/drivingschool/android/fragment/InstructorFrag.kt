package com.drivingschool.android.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.drivingschool.android.R
import com.drivingschool.android.adapters.InstructorsAdapter
import com.drivingschool.android.customviews.CustomTextView
import com.drivingschool.android.customviews.SimpleDividerItemDecoration
import com.drivingschool.android.models.InstructorModel
import com.drivingschool.android.ui.MainActivity
import kotlinx.android.synthetic.main.add_new_instr.view.*
import kotlinx.android.synthetic.main.layout_instructor.view.*


class InstructorFrag : Fragment(), InstructorsAdapter.mClickListener {


    private lateinit var viewAdapter: InstructorsAdapter
    private lateinit var layoutmanager: RecyclerView.LayoutManager
    private val instructorModelArrayList = ArrayList<InstructorModel>()
    private lateinit var alertDialog : AlertDialog


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.layout_instructor, container, false)

        val mainActivity = activity as MainActivity
        mainActivity.setToolbarTittle("Instructor")


        view.recycler_inst.setHasFixedSize(true)
        viewAdapter = InstructorsAdapter(instructorModelArrayList, context, this)
        layoutmanager = LinearLayoutManager(context)
        view.recycler_inst.layoutManager = layoutmanager
        view.recycler_inst.itemAnimator = DefaultItemAnimator()
        view.recycler_inst.addItemDecoration(SimpleDividerItemDecoration(context))
        view.recycler_inst.adapter = viewAdapter


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



        instructorData()

        return view
    }

    private fun instructorData() {

        val instructorModel = InstructorModel("Mac sanah")
        instructorModelArrayList.add(instructorModel)

        val instructorModel1 = InstructorModel("Jack sanah")
        instructorModelArrayList.add(instructorModel1)

        val instructorModel2 = InstructorModel("Dack sanah")
        instructorModelArrayList.add(instructorModel2)


        val instructorModel3 = InstructorModel("Roni sanah")
        instructorModelArrayList.add(instructorModel3)

        viewAdapter.notifyDataSetChanged()

    }





    override fun mClick(v: View?, position: Int) {


        val someFragment = InstructorDetailFrag()
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.main_container, someFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }


}
