package com.drivingschool.android.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.drivingschool.android.R
import com.drivingschool.android.customviews.CustomTextView
import com.drivingschool.android.models.ReviewsList

import java.util.ArrayList


class ReviewAdapter(private val reviewsListArrayList: ArrayList<ReviewsList>,
                    private val context: Context,
                    private val mReplyCallback: OnReplyClick) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {


    interface OnReplyClick {
        fun onReplyClick(v: View,pos: Int)
        fun onDetailClick(v: View,pos: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_review, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewAdapter.ViewHolder, position: Int) {

        val reviewsList = reviewsListArrayList[position]

        holder.tvVehicle.text = reviewsList.vehicleName

        holder.tvReply.setOnClickListener {

            mReplyCallback!!.onReplyClick(holder.tvReply,position)
        }

        holder.tvDetail.setOnClickListener {
            mReplyCallback!!.onDetailClick(holder.tvReply,position)

        }

    }

    override fun getItemCount(): Int {
        return reviewsListArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvVehicle: CustomTextView
        val tvReply: CustomTextView
        val tvDetail: CustomTextView


        init {

            tvVehicle = itemView.findViewById(R.id.tvVehicle)
            tvReply = itemView.findViewById(R.id.tvReply)
            tvDetail = itemView.findViewById(R.id.tvDetail)


        }




    }
}
