package com.sundin.beso.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.auth.User
import com.sundin.beso.R
import com.sundin.beso.database.FirestoreDB
import com.sundin.beso.databinding.ActivitySignUpBinding

class SignUpActivity: BaseActivity() {

    lateinit var bindingSignUpScreen: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_sign_up)

        bindingSignUpScreen = ActivitySignUpBinding.inflate(layoutInflater)

        setupActionBar()

        val btnSignUp = bindingSignUpScreen.btnSignUpSubmit
        btnSignUp.setOnClickListener {
            registerUser()
        }
    }


    private fun setupActionBar() {
        val toolbarSignUpActivity = bindingSignUpScreen.toolbarSignUpActivity
        setSupportActionBar(toolbarSignUpActivity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }

        toolbarSignUpActivity.setNavigationOnClickListener { onBackPressed() }
    }


    private fun registerUser() {
        // Here we get the text from editText and trim the space
        val etPersonName = bindingSignUpScreen.etPersonName
        val etEmail = bindingSignUpScreen.etEmail
        val etPassword = bindingSignUpScreen.etPassword

        val name: String = etPersonName.text.toString().trim { it <= ' ' }
        val email: String = etEmail.text.toString().trim { it <= ' ' }
        val password: String = etPassword.text.toString().trim { it <= ' ' }

        if (validateForm(name, email, password)) {
            // Show the progress dialog.
            showProgressDialog(resources.getString(R.string.please_wait))
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->

                        // If the registration is successfully done
                        if (task.isSuccessful) {

                            // Firebase registered user
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            // Registered Email
                            val registeredEmail = firebaseUser.email!!

//                            val user = User(
//                                firebaseUser.uid, name, registeredEmail
//                            )

                            // call the registerUser function of FirestoreClass to make an entry in the database.
//                            FirestoreDB().registerUser(this@SignUpActivity, firebaseUser)
//                        } else {
//                            Toast.makeText(
//                                this@SignUpActivity,
//                                task.exception!!.message,
//                                Toast.LENGTH_SHORT
//                            ).show()
                        }
                    })
        }
    }

    private fun validateForm(name: String, email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                showErrorSnackBar("Please enter name.")
                false
            }
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter email.")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please enter password.")
                false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                showErrorSnackBar("Please enter a proper email.")
                false
            }
            else -> {
                true
            }
        }
    }


    fun userRegisteredSuccess() {

        Toast.makeText(
            this@SignUpActivity,
            "You have successfully registered!",
            Toast.LENGTH_SHORT
        ).show()

        // Hide the progress dialog
        hideProgressDialog()

        /**
         * Here the new user registered is automatically signed-in so we just sign-out the user from firebase
         * and send him to Intro Screen for Sign-In
         */
        FirebaseAuth.getInstance().signOut()
        // Finish the Sign-Up Screen
        finish()
    }
}