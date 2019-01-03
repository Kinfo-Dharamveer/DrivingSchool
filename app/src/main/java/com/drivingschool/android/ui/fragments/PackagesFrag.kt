package com.drivingschool.android.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.drivingschool.android.R
import com.drivingschool.android.adapters.PackageAdapter
import com.drivingschool.android.customviews.SimpleDividerItemDecoration
import com.drivingschool.android.models.PackageList
import com.drivingschool.android.ui.MainActivity

class PackagesFrag: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: PackageAdapter
    private var packageArrayList = ArrayList<PackageList>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.layout_packages,container,false)

        val mainActivity = activity as MainActivity
        mainActivity.setToolbarTittle("Packages")

        recyclerView = view.findViewById(R.id.recyclerview_packages)

        recyclerView.setHasFixedSize(true)
        viewAdapter = PackageAdapter(packageArrayList,context)
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(SimpleDividerItemDecoration(context))
        recyclerView.adapter = viewAdapter

        packageData()


        return view
    }

    private fun packageData() {

        var packageList  = PackageList("Play Station","12")
        packageArrayList.add(packageList)

        var packageList1  = PackageList("Play Station","12")
        packageArrayList.add(packageList1)


        var packageList2  = PackageList("Play Station","12")
        packageArrayList.add(packageList2)


        var packageList3  = PackageList("Play Station","12")
        packageArrayList.add(packageList3)

    }



}