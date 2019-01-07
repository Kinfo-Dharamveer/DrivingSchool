package com.drivingschool.android.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.drivingschool.android.R
import kotlinx.android.synthetic.main.school_review_frag.view.*
import com.androidnetworking.error.ANError
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.drivingschool.android.AppConstants
import com.drivingschool.android.adapters.SchoolPlansAdapter
import com.drivingschool.android.customviews.SimpleDividerItemDecoration
import com.drivingschool.android.response.schooldetail.PlanPayload
import org.json.JSONObject


class SchoolReviewFragment : Fragment() {

    private lateinit var schoolPlansAdapter: SchoolPlansAdapter
    private lateinit var plansArrayList: ArrayList<PlanPayload>
    private lateinit var layoutmanager: RecyclerView.LayoutManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.school_review_frag, container, false)

        view.plansRecycView.setHasFixedSize(true)
        layoutmanager = LinearLayoutManager(context)
        view.plansRecycView.layoutManager = layoutmanager
        view.plansRecycView.itemAnimator = DefaultItemAnimator()
        view.plansRecycView.addItemDecoration(SimpleDividerItemDecoration(context))

        plansArrayList = ArrayList<PlanPayload>()

        val school_id = arguments!!.getInt(AppConstants.SCHOOL_ID)

        AndroidNetworking.get(resources.getString(R.string.api_url) + "school/{id}")
                .addPathParameter("id", "1")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {

                        Toast.makeText(context,"Success", Toast.LENGTH_SHORT).show()
                        val jsonObject = JSONObject(response.toString())

                        val jsonArray = jsonObject.getJSONArray("plan")

                        val length = jsonArray.length()

                        val planPayload  = PlanPayload()

                        for (i in 0 until length) {
                            val jsonObj = jsonArray.getJSONObject(i)
                            planPayload.name = jsonObj.getString("name")
                            planPayload.price = jsonObj.getString("price")
                            planPayload.desc = jsonObj.getString("description")
                        }
                        plansArrayList.add(planPayload)
                        schoolPlansAdapter = SchoolPlansAdapter(plansArrayList, context)
                        view.plansRecycView.adapter = schoolPlansAdapter

                    }

                    override fun onError(anError: ANError?) {
                        Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()

                    }

                })



        return view
    }
}
