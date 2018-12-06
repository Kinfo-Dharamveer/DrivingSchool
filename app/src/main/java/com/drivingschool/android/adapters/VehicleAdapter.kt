package com.drivingschool.android.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.drivingschool.android.R
import com.drivingschool.android.customviews.CustomTextView
import com.drivingschool.android.models.VehicleList

import java.util.ArrayList

class VehicleAdapter(private val vehicleListArrayList: ArrayList<VehicleList>, private val context: Context) : RecyclerView.Adapter<VehicleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_vehicles, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val vehicleList = vehicleListArrayList[position]

        holder.tvManufacturer.text = vehicleList.carManufactures
        holder.imageCar.setImageResource(vehicleList.carImages)

    }

    override fun getItemCount(): Int {
        return vehicleListArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvManufacturer: CustomTextView
        val imageCar: ImageView


        init {

            tvManufacturer = itemView.findViewById(R.id.tvManufacturer)
            imageCar = itemView.findViewById(R.id.imageCar)
        }
    }
}
