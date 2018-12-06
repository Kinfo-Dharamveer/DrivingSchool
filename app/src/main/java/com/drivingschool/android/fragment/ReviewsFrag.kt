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
import android.widget.EditText

import com.drivingschool.android.R
import com.drivingschool.android.adapters.ReviewAdapter
import com.drivingschool.android.customviews.CustomTextView
import com.drivingschool.android.customviews.SimpleDividerItemDecoration
import com.drivingschool.android.models.ReviewsList
import com.drivingschool.android.ui.MainActivity
import kotlinx.android.synthetic.main.layout_review.view.*

class ReviewsFrag : Fragment(), ReviewAdapter.OnReplyClick {



    private var reviewArrayList = ArrayList<ReviewsList>()
    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var alertDialog : AlertDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.layout_review, container, false)

        val mainActivity = activity as MainActivity
        mainActivity.setToolbarTittle("Reviews")

        view.recyclerview_review.setHasFixedSize(true)
        reviewAdapter = ReviewAdapter(reviewArrayList,context!!,this)
        layoutManager = LinearLayoutManager(context)
        view.recyclerview_review.layoutManager  = layoutManager
        view.recyclerview_review.itemAnimator = DefaultItemAnimator()
        view.recyclerview_review.addItemDecoration(SimpleDividerItemDecoration(context))
        view.recyclerview_review.adapter = reviewAdapter

        reviewsData()

        return view
    }

    override fun onReplyClick(v: View, pos: Int) {


        val dialogBuilder = AlertDialog.Builder(context!!)
        // ...Irrelevant code for customizing the buttons and title
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.reply_reviews, null)
        dialogBuilder.setView(dialogView)

        val userName = dialogView.findViewById(R.id.txtUserName) as CustomTextView
        val reply = dialogView.findViewById(R.id.edReply) as EditText
        val submitReply = dialogView.findViewById(R.id.txtSubmitReply) as CustomTextView
        val cancelReply = dialogView.findViewById(R.id.txtCancelReply) as CustomTextView

        userName.setText("Name")

        alertDialog = dialogBuilder.create()
        alertDialog.show()

        submitReply.setOnClickListener {
            alertDialog.dismiss()
        }

        cancelReply.setOnClickListener {
            alertDialog.dismiss()
        }
    }

    override fun onDetailClick(v: View, pos: Int) {
        val someFragment = ReviewsDetails()
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.main_container, someFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()    }



    private fun reviewsData() {

        val reviewsList = ReviewsList("Alfa-Romeo Stelvio")
        reviewArrayList.add(reviewsList)

        val reviewsLis1t = ReviewsList("Alfa-Romeo Stelvio")
        reviewArrayList.add(reviewsLis1t)

        val reviewsList2 = ReviewsList("Alfa-Romeo Stelvio")
        reviewArrayList.add(reviewsList2)


        val reviewsList3 = ReviewsList("Alfa-Romeo Stelvio")
        reviewArrayList.add(reviewsList3)


        val reviewsList4 = ReviewsList("Alfa-Romeo Stelvio")
        reviewArrayList.add(reviewsList4)

    }
}
