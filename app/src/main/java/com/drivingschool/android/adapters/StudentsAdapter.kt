package com.drivingschool.android.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.drivingschool.android.R
import com.drivingschool.android.customviews.CustomTextView
import com.drivingschool.android.models.StudentsList

import java.util.ArrayList

class StudentsAdapter(private val studentsListArrayList: ArrayList<StudentsList>, private val context: Context) : RecyclerView.Adapter<StudentsAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsAdapter.Holder {

        val view = LayoutInflater.from(context).inflate(R.layout.row_student, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: StudentsAdapter.Holder, position: Int) {

        val studentsList = studentsListArrayList[position]

        holder.tvStudentName.text = studentsList.studentName
        holder.tvCountry.text = studentsList.countryName
    }

    override fun getItemCount(): Int {
        return studentsListArrayList.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvStudentName: CustomTextView
        val tvCountry: CustomTextView
        private val tvDetails: CustomTextView


        init {

            tvStudentName = itemView.findViewById(R.id.tvStudentName)
            tvCountry = itemView.findViewById(R.id.tvCountry)
            tvDetails = itemView.findViewById(R.id.tvDetails)

        }
    }
}
