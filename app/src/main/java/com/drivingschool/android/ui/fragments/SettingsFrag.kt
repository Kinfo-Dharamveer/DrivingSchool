package com.drivingschool.android.ui.fragments

import `in`.mayanknagwanshi.imagepicker.imageCompression.ImageCompressionListener
import `in`.mayanknagwanshi.imagepicker.imagePicker.ImagePicker
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.drivingschool.android.R
import com.drivingschool.android.ui.DashboardActivity
import kotlinx.android.synthetic.main.layout_settings.view.*
import java.io.File

class SettingsFrag: Fragment(), AdapterView.OnItemSelectedListener {


    private val IMAGE_CHOOSER = 1
    private lateinit var imagePicker: ImagePicker
    private lateinit var viewOfLayout: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewOfLayout = inflater.inflate(R.layout.layout_settings,container,false)

        val mainActivity = activity as DashboardActivity
        mainActivity.setToolbarTittle("Settings")

        imagePicker =  ImagePicker()

        viewOfLayout.edSchoolName.setText("Arya SchoolPayload")
        viewOfLayout.edAddress.setText("Gustavo Fring Avenue, Juarez, ABQ, New Mexico")
        viewOfLayout.edGoogleMapLink.setText("https://www.google.com/maps")
        viewOfLayout.edMobile.setText("9779269052")
        viewOfLayout.edEmailSettings.setText("mca2012veer@gmail.com")
        viewOfLayout.edFacebook.setText("https://www.facebook.com/")
        viewOfLayout.edTwitter.setText("https://twitter.com")
        viewOfLayout.edGooglePlus.setText("https://plus.google.com")
        viewOfLayout.edLinkedIn.setText("https://www.linkedin.com/")
        viewOfLayout.edYouTube.setText("https://www.youtube.com/")

        viewOfLayout.spinCountry.setOnItemSelectedListener(this)

        // Spinner Drop down elements
        //Convert it into a list
        val allCountriesList = resources.getStringArray(R.array.countries_array)

        // Creating adapter for spinner
        val dataAdapter = ArrayAdapter(context,
                R.layout.spinner_item, allCountriesList)

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        viewOfLayout.spinCountry.adapter = dataAdapter

        viewOfLayout.btnChooseFile.setOnClickListener {
          //  imagePicker.withFragment(this).start()
        }

        viewOfLayout.btnSaveChanges.setOnClickListener {
            Toast.makeText(context,"Data saved", Toast.LENGTH_SHORT).show()
        }


        return viewOfLayout
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ImagePicker.SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
            //Add compression listener if withCompression is set to true

            imagePicker.addOnCompressListener(object : ImageCompressionListener {
                override fun onStart() {

                }

                override fun onCompressed(filePath: String) {//filePath of the compressed image
                    //convert to bitmap easily
                    val f = File(filePath)
                    val imageName = f.getName()
                    val selectedImage = BitmapFactory.decodeFile(filePath)
                    viewOfLayout.imageLogo.setImageBitmap(selectedImage)
                    viewOfLayout.tvImageName.setText(imageName)

                }
            })

        }
        //call the method 'getImageFilePath(Intent data)' even if compression is set to false
        val filePath = imagePicker.getImageFilePath(data)



        if (filePath != null) {//filePath will return null if compression is set to true
            val f = File(filePath)
            val imageName = f.getName()
            val selectedImage = BitmapFactory.decodeFile(filePath)
            viewOfLayout.imageLogo.setImageBitmap(selectedImage)
            viewOfLayout.tvImageName.setText(imageName)

        }


        super.onActivityResult(requestCode, resultCode, data)
    }


    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }


}