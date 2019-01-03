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
import android.widget.Button
import android.widget.Spinner
import com.drivingschool.android.R
import com.drivingschool.android.adapters.VehicleAdapter
import com.drivingschool.android.customviews.CustomTextView
import com.drivingschool.android.customviews.SimpleDividerItemDecoration
import com.drivingschool.android.models.VehicleList
import com.drivingschool.android.ui.MainActivity
import kotlinx.android.synthetic.main.layout_vehicles.view.*

class VehicleFrag : Fragment() {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: VehicleAdapter
    private var vehicleArrayList = ArrayList<VehicleList>()
    private lateinit var alertDialog: AlertDialog


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.layout_vehicles, container, false)

        val mainActivity = activity as MainActivity
        mainActivity.setToolbarTittle("Vehicle")

        view.recyclerview_vehicle.setHasFixedSize(true)
        viewAdapter = VehicleAdapter(vehicleArrayList, context!!)
        layoutManager = LinearLayoutManager(context)
        view.recyclerview_vehicle.layoutManager = layoutManager
        view.recyclerview_vehicle.itemAnimator = DefaultItemAnimator()
        view.recyclerview_vehicle.addItemDecoration(SimpleDividerItemDecoration(context))
        view.recyclerview_vehicle.adapter = viewAdapter


        view.addNewVehicle.setOnClickListener {

            val dialogBuilder = AlertDialog.Builder(context!!)
            // ...Irrelevant code for customizing the buttons and title
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.add_new_vehicle, null)
            dialogBuilder.setView(dialogView)

            val spinVehType = dialogView.findViewById(R.id.spinVehType) as Spinner
            val spinSteering = dialogView.findViewById(R.id.spinSteering) as Spinner
            val chooseFile = dialogView.findViewById(R.id.btnChooseFileVehicle) as Button
            val fileChooseStatus = dialogView.findViewById(R.id.tvFileStatus) as CustomTextView
            val submit = dialogView.findViewById(R.id.submitInst) as CustomTextView
            val cancel = dialogView.findViewById(R.id.tvCancel) as CustomTextView

            alertDialog = dialogBuilder.create()
            alertDialog.show()


            chooseFile.setOnClickListener {


            }

            submit.setOnClickListener {
                alertDialog.dismiss()
            }

            cancel.setOnClickListener {
                alertDialog.dismiss()
            }


        }

        vehicleData()

        return view
    }

    private fun vehicleData() {

        var vehicleList = VehicleList(R.drawable.car, "Alfa-Romeo")
        vehicleArrayList.add(vehicleList)

        var vehicleList1 = VehicleList(R.drawable.car2, "Bentley")
        vehicleArrayList.add(vehicleList1)

        var vehicleList2 = VehicleList(R.drawable.car3, "Porsche")
        vehicleArrayList.add(vehicleList2)

        var vehicleList3 = VehicleList(R.drawable.car2, "Chevrolet")
        vehicleArrayList.add(vehicleList3)

        var vehicleList4 = VehicleList(R.drawable.car, "Maseratti")
        vehicleArrayList.add(vehicleList4)


    }


}