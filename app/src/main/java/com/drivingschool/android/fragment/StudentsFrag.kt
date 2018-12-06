package com.drivingschool.android.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.drivingschool.android.R
import com.drivingschool.android.adapters.StudentsAdapter
import com.drivingschool.android.customviews.SimpleDividerItemDecoration
import com.drivingschool.android.models.StudentsList
import com.drivingschool.android.ui.MainActivity

class StudentsFrag : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: StudentsAdapter
    private var studentArrayList = ArrayList<StudentsList>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.layout_students, container, false)

        val mainActivity = activity as MainActivity
        mainActivity.setToolbarTittle("Students")

        recyclerView = view.findViewById(R.id.recyclerview_students)


        recyclerView.setHasFixedSize(true)
        viewAdapter = StudentsAdapter(studentArrayList,context!!)
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(SimpleDividerItemDecoration(context))
        recyclerView.adapter = viewAdapter

        studentsData()


        return view
    }

    private fun studentsData() {

        val studentsList = StudentsList("Mark","Ghana")
        studentArrayList.add(studentsList)

        val studentsList1 = StudentsList("Mark","Ghana")
        studentArrayList.add(studentsList1)

        val studentsList2 = StudentsList("Mark","Ghana")
        studentArrayList.add(studentsList2)

        val studentsList3 = StudentsList("Mark","Ghana")
        studentArrayList.add(studentsList3)

        val studentsList4 = StudentsList("Mark","Ghana")
        studentArrayList.add(studentsList4)


    }



}
