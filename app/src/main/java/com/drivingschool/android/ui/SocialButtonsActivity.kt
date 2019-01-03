package com.drivingschool.android.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import com.drivingschool.android.AppConstants
import com.drivingschool.android.R
import com.drivingschool.android.customviews.CustomTextView
import com.drivingschool.android.data.MessageEvent
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.orhanobut.hawk.Hawk
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.*

class SocialButtonsActivity : BaseActivity() {

    private var btnRegisterSchool: Button? = null
    private var txtForgotPsw: CustomTextView? = null
    private var btnLoginAsSchool: Button? = null
    private var btnLoginFacebook: FrameLayout? = null
    private var btnLoginGoogle: FrameLayout? = null
    private var callbackManager: CallbackManager? = null
    private var fbLogin: LoginButton? = null
    private var googleLogin: SignInButton? = null
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private val RC_SIGN_IN = 1

    internal lateinit var notInternetLayout: LinearLayout
    internal lateinit var main_layout: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social)

        ReferenceControl()
    }

    private fun ReferenceControl() {

        main_layout = findViewById(R.id.main)
        notInternetLayout = findViewById(R.id.notInternetLayout)


        btnRegisterSchool = findViewById(R.id.btnRegisterSchool)
        btnLoginAsSchool = findViewById(R.id.btnLoginAsSchool)
        txtForgotPsw = findViewById(R.id.txtForgotPsw)

        btnLoginFacebook = findViewById(R.id.btnLoginFacebook)
        btnLoginGoogle = findViewById(R.id.btnLoginGoogle)
        fbLogin = findViewById(R.id.fbLogin)
        googleLogin = findViewById(R.id.googleLogin)

        btnRegisterSchool!!.setOnClickListener {
           /* val i = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(i)*/
        }

        btnLoginAsSchool!!.setOnClickListener {
           /* val i = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(i)*/
        }

        txtForgotPsw!!.setOnClickListener {
         /*   val i = Intent(applicationContext, ForgotPasswordActivity::class.java)
            startActivity(i)*/
        }
        facebookLogin()
        googleSignIn()


        btnLoginFacebook!!.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(
                    this,
                    Arrays.asList("user_photos", "email", "user_birthday", "public_profile")
            )

        }

    }

    private fun googleSignIn() {

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        googleLogin!!.setSize(SignInButton.SIZE_STANDARD)
        googleLogin!!.setColorScheme(SignInButton.COLOR_LIGHT)

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        val account = GoogleSignIn.getLastSignedInAccount(this)
        //updateUI(account);

        btnLoginGoogle!!.setOnClickListener {
            val signInIntent = mGoogleSignInClient!!.getSignInIntent()
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

    }

/*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }

    }
*/

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            //updateUI(account);

            Hawk.put(AppConstants.IS_LOGIN, true)
            Hawk.put(AppConstants.USER_EMAIL, account!!.email)
            Hawk.put(AppConstants.USER_PASSWORD, account!!.displayName)

            goToWelcomeScreen()

        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("", "signInResult:failed code=" + e.statusCode)
            // updateUI(null);
        }

    }


    private fun facebookLogin() {
        callbackManager = CallbackManager.Factory.create()

        // Register a callback to respond to the user
        fbLogin!!.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                setResult(Activity.RESULT_OK)

                val request = GraphRequest.newMeRequest(
                        loginResult.accessToken) { me, response ->
                    if (response.error != null) {
                        // handle error
                    } else {

                        val user_lastname = me.optString("last_name")
                        val user_firstname = me.optString("first_name")
                        val user_email = response.jsonObject.optString("email")


                        Hawk.put(AppConstants.IS_LOGIN, true)
                        Hawk.put(AppConstants.USER_NAME, user_firstname)
                        Hawk.put(AppConstants.USER_EMAIL, user_email)
                        goToWelcomeScreen()
                    }
                }

                val parameters = Bundle()
                parameters.putString("fields", "last_name,first_name,email")
                request.parameters = parameters
                request.executeAsync()


            }

            override fun onCancel() {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }

            override fun onError(e: FacebookException) {
                // Handle exception
            }
        })

    }

    private fun goToWelcomeScreen() {

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()

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
            main_layout.setVisibility(View.GONE)
           // Toast.makeText(this,"NOT CONNECTED", Toast.LENGTH_SHORT).show()

        }
        else{
            main_layout.setVisibility(View.VISIBLE)
            notInternetLayout.visibility = View.GONE
           // Toast.makeText(this,"CONNECTED", Toast.LENGTH_SHORT).show()

        }

    }

}
