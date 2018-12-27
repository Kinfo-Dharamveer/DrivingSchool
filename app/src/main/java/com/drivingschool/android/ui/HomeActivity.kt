package com.drivingschool.android.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import com.drivingschool.android.R
import com.drivingschool.android.data.MessageEvent
import com.drivingschool.android.fragment.FirstHomeFrag
import com.drivingschool.android.fragment.HomeFrag
import com.drivingschool.android.fragment.SchoolsFrag
import kotlinx.android.synthetic.main.activity_home.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class HomeActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        if (savedInstanceState == null) {
            val f1 = FirstHomeFrag()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.home_container, f1)
            fragmentTransaction.commit()
        }

        txtHome.setOnClickListener {

            val f1 = FirstHomeFrag()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.home_container, f1)
            fragmentTransaction.commit()

        }
        txtSchools.setOnClickListener {
            val f1 = SchoolsFrag()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.home_container, f1)
            fragmentTransaction.commit()

        }
        txtLogin.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }

    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onEvent(status: MessageEvent){

        if (status.status.contains("NOT_CONNECT")){

            notInternetLayoutHome.visibility = View.VISIBLE
            home_container.setVisibility(View.GONE)
            Toast.makeText(this,"NOT CONNECTED", Toast.LENGTH_SHORT).show()

        }
        else
        {
            home_container.setVisibility(View.VISIBLE)
            notInternetLayoutHome.visibility = View.GONE
            Toast.makeText(this,"CONNECTED", Toast.LENGTH_SHORT).show()

        }

    }


}
