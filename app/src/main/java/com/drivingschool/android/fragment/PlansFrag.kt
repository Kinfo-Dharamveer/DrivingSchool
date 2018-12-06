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
import com.drivingschool.android.adapters.PlansAdapter
import com.drivingschool.android.customviews.SimpleDividerItemDecoration
import com.drivingschool.android.models.PlansList
import com.drivingschool.android.ui.MainActivity


class PlansFrag: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: PlansAdapter
    private var plansArrayList = ArrayList<PlansList>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.layout_plans,container,false)

        val mainActivity = activity as MainActivity
        mainActivity.setToolbarTittle("Manage Plans")

        recyclerView = view.findViewById(R.id.recyclerview_plans)

        recyclerView.setHasFixedSize(true)
        viewAdapter = PlansAdapter(plansArrayList,context)
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(SimpleDividerItemDecoration(context))

        recyclerView.adapter = viewAdapter

        plansData()

        return view

    }

    private fun plansData() {

        val plansList = PlansList("Started","Any type","Any type of","56")
        plansArrayList.add(plansList)

        val plansList2 = PlansList("Started","Any type","Any type of","56")
        plansArrayList.add(plansList2)

        val plansList3 = PlansList("Started","Any type","Any type of","56")
        plansArrayList.add(plansList3)

        val plansList4 = PlansList("Started","Any type","Any type of","56")
        plansArrayList.add(plansList4)
    }



}