package com.drivingschool.android.ui

import android.app.AlertDialog
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle

import android.view.MenuItem
import android.view.View

import android.support.v7.widget.PopupMenu
import android.support.v7.widget.Toolbar
import android.text.Spannable
import android.text.SpannableString
import android.view.Menu
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout


import com.drivingschool.android.R
import com.drivingschool.android.customviews.CustomTextView
import com.nex3z.notificationbadge.NotificationBadge
import com.drivingschool.android.AppConstants
import com.drivingschool.android.customviews.CustomTypefaceSpan
import com.drivingschool.android.data.Constants.CONNECTIVITY_ACTION
import com.drivingschool.android.data.MessageEvent
import com.drivingschool.android.service.NetworkChangeReceiver
import com.drivingschool.android.ui.fragments.*
import com.drivingschool.android.utils.NetworkUtil
import com.orhanobut.hawk.Hawk
import dmax.dialog.SpotsDialog
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class DashboardActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener,
        PopupMenu.OnMenuItemClickListener{

    var imgNotification: ImageView? = null
    private val mCount = 0

    lateinit var toolbar: Toolbar
    lateinit var toolbarTitle: CustomTextView
    lateinit var action_dots: ImageView
    lateinit var badge: NotificationBadge
    lateinit var imgDrawer: ImageView

    var frameLayout: FrameLayout? = null
    lateinit var drawLayout: DrawerLayout
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    var navigationView: NavigationView? = null
    var progrDialog: AlertDialog? = null



    internal lateinit var notInternetLayout: LinearLayout
    internal lateinit var main_container: FrameLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        intentFilter = IntentFilter()
        intentFilter.addAction(CONNECTIVITY_ACTION)
        receiver = NetworkChangeReceiver()

        if (NetworkUtil.getConnectivityStatus(this) > 0)
            println("Connect")
        else
            println("No connection")


        findIds()

        if (savedInstanceState == null) {
            val f1 = HomeFrag()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.main_container, f1)
            fragmentTransaction.commit()
        }


        progrDialog = SpotsDialog(this,R.style.Custom)


        val m = navigationView!!.getMenu()
        for (i in 0 until m.size()) {
            val mi = m.getItem(i)

            //for aapplying a font to subMenu ...
            val subMenu = mi.subMenu
            if (subMenu != null && subMenu.size() > 0) {
                for (j in 0 until subMenu.size()) {
                    val subMenuItem = subMenu.getItem(j)
                    applyFontToMenuItem(subMenuItem)
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi)
        }

        //txtUserName.setText(Hawk)

        action_dots.setOnClickListener {
            showPopUp(action_dots)
        }

        // ActionBarDrawerToggle ties together the the proper interactions between the sliding drawer and the action bar app icon
        actionBarDrawerToggle = object : ActionBarDrawerToggle(
                this, /* host Activity */
                drawLayout, /* DrawerLayout object */
                R.string.open_drawer, /* "open drawer" description for accessibility */
                R.string.close_drawer)    /* "close drawer" description for accessibility */ {

            override fun onDrawerClosed(drawerView: View) {
                invalidateOptionsMenu()
                super.onDrawerClosed(drawerView)
            }

            override fun onDrawerOpened(drawerView: View) {
                invalidateOptionsMenu()
                super.onDrawerOpened(drawerView)
            }

        }
        drawLayout.setDrawerListener(actionBarDrawerToggle)

        actionBarDrawerToggle!!.syncState()
        navigationView!!.setNavigationItemSelectedListener(this)

        imgDrawer.setOnClickListener {
            drawLayout.openDrawer(GravityCompat.START)
        }

    }

    private fun findIds() {
        // Set a Toolbar to replace the ActionBar.
        toolbar = findViewById(R.id.main_toolbar)

        main_container = findViewById(R.id.main_container)
        notInternetLayout = findViewById(R.id.notInternetLayout)
        imgDrawer = findViewById(R.id.imgopenDrawer)
        toolbarTitle = findViewById(R.id.toolbarText)
        badge = findViewById(R.id.badge)

        frameLayout = findViewById(R.id.content_frame)
        drawLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        action_dots = findViewById(R.id.action_dots)
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


            notInternetLayout.visibility = View.VISIBLE
            main_container.setVisibility(View.GONE)
          //  Toast.makeText(this,"NOT CONNECTED",Toast.LENGTH_SHORT).show()

        }
        else{
            main_container.setVisibility(View.VISIBLE)
            notInternetLayout.visibility = View.GONE
          //  Toast.makeText(this,"CONNECTED",Toast.LENGTH_SHORT).show()

        }

    }


    fun setToolbarTittle(title: String) {

        toolbarTitle.setText(title)
    }

    private fun showPopUp(v: View) {

        val popup = PopupMenu(this, v)

        // This activity implements OnMenuItemClickListener
        popup.setOnMenuItemClickListener(this)
        popup.inflate(R.menu.main)
        popup.show()
    }

    private fun applyFontToMenuItem(subMenuItem: MenuItem?) {
        val font = Typeface.createFromAsset(assets, "fonts/Montserrat-Regular.ttf")
        val mNewTitle = SpannableString(subMenuItem!!.getTitle())
        mNewTitle.setSpan(CustomTypefaceSpan("", font), 0, mNewTitle.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        subMenuItem.setTitle(mNewTitle)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main,menu)
        return super.onCreateOptionsMenu(menu)
    }



    /* We can override onBackPressed method to toggle navigation drawer*/
    override fun onBackPressed() {
        if (drawLayout.isDrawerOpen(GravityCompat.START)) {
            drawLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {


        return when (item!!.itemId) {
            R.id.action_profile -> {

                true
            }
            R.id.action_curplan -> {

                true
            }
            R.id.action_logout -> {
                Hawk.delete(AppConstants.USER_ID)

                val i = Intent(applicationContext,HomeActivity::class.java)
                i.putExtra(AppConstants.OPEN_LOGIN_FRAG,1)
                startActivity(i)
                finish()
                true
            }

            else -> false
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val manager = getSupportFragmentManager()
        val transaction = manager.beginTransaction()


        when (item.itemId) {

            R.id.nav_dash -> {

                val fragment = HomeFrag()
                transaction.replace(R.id.main_container, fragment,
                        fragment.javaClass.getSimpleName()).addToBackStack(null).commit()
                getSupportFragmentManager().executePendingTransactions();

            }

            R.id.nav_instr -> {

                val fragment = InstructorFrag()
                transaction.replace(R.id.main_container, fragment,
                        fragment.javaClass.getSimpleName()).addToBackStack(null).commit()

            }

            R.id.nav_students -> {

                val fragment = StudentsFrag()
                transaction.replace(R.id.main_container, fragment,
                        fragment.javaClass.getSimpleName()).addToBackStack(null).commit()

            }

            R.id.nav_reviews -> {

                val fragment = ReviewsFrag()
                transaction.replace(R.id.main_container, fragment,
                        fragment.javaClass.getSimpleName()).addToBackStack(null).commit()


            }

            R.id.nav_vehicles -> {

                val fragment = VehicleFrag()
                transaction.replace(R.id.main_container, fragment,
                        fragment.javaClass.getSimpleName()).addToBackStack(null).commit()

            }

            R.id.nav_packa -> {

                val fragment = PackagesFrag()
                transaction.replace(R.id.main_container, fragment,
                        fragment.javaClass.getSimpleName()).addToBackStack(null).commit()


            }
            R.id.nav_settings -> {

                val fragment = SettingsFrag()
                transaction.replace(R.id.main_container, fragment,
                        fragment.javaClass.getSimpleName()).addToBackStack(null).commit()

            }
            R.id.nav_plans -> {
                val fragment = PlansFrag()
                transaction.replace(R.id.main_container, fragment,
                        fragment.javaClass.getSimpleName()).addToBackStack(null).commit()


            }

            R.id.nav_contact_us -> {
                val fragment = AboutUs()
                transaction.replace(R.id.main_container,fragment,
                        fragment.javaClass.simpleName).addToBackStack(null).commit()
            }
        }

        drawLayout.closeDrawer(GravityCompat.START)

        return true

    }



}
