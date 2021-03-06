package com.twoplusapp.twoplus.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.twoplusapp.twoplus.R
import com.twoplusapp.twoplus.database.FirestoreClass
import com.twoplusapp.twoplus.models.UserModel


class SignUpActivity: BaseActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setupActionBar()

        auth = FirebaseAuth.getInstance()

//        val btnBackSignUpScreen: ImageView = findViewById()
        val btnSignUpSubmit: Button = findViewById(R.id.btnSignUpSubmit)
        btnSignUpSubmit.setOnClickListener {
            registerUser()
        }
    }


    private fun setupActionBar() {
        val toolbarSignUpActivity: androidx.appcompat.widget.Toolbar? =
            findViewById(R.id.toolbarSignUpActivity)

        toolbarSignUpActivity?.setOnClickListener {
            onBackPressed()
        }
    }


    private fun registerUser() {
        // Here we get the text from editText and trim the space
//        val etPersonName = bindingSignUpScreen.etPersonName
//        val etEmail = bindingSignUpScreen.etEmail
//        val etPassword = bindingSignUpScreen.etPassword

        val etPersonFirstName: EditText = findViewById(R.id.etPersonFirstName)
        val etPersonLastName: EditText = findViewById(R.id.etPersonLastName)
        val etEmail: EditText = findViewById(R.id.etEmailSignUp)
        val etPassword: EditText = findViewById(R.id.etPasswordSignUp)

        val first_name: String = etPersonFirstName.text.toString().trim( { it <= ' ' })
        val last_name: String = etPersonLastName.text.toString().trim { it <= ' '}
        val email: String = etEmail.text.toString().trim { it <= ' ' }
        val password: String = etPassword.text.toString().trim { it <= ' ' }

        if (validateForm(first_name, last_name, email, password)) {
            // Show the progress dialog.
            showProgressDialog(resources.getString(R.string.please_wait))

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    // If the registration is successfully done
                    if (task.isSuccessful) {
                        Log.d("signup", "You have successfully signed up.")

                        // Firebase registered user
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        // Registered Email
                        val registeredEmail = firebaseUser.email!!

                        val user = UserModel(
                            firebaseUser.uid,
                            registeredEmail,
                            R.drawable.man1_stock_photo.toString(),
                            first_name,
                            last_name,
                        )
                        // call the registerUser function of FirestoreClass to make an entry in the database.
                        FirestoreClass().registerUser(this@SignUpActivity, user)

                    } else {
                        Toast.makeText(
                            this@SignUpActivity,
                            task.exception!!.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun validateForm(first_name: String, last_name: String, email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(first_name) -> {
                showErrorSnackBar("Please enter first name.")
                false
            }
            TextUtils.isEmpty(last_name) -> {
                showErrorSnackBar("Please enter last name.")
                false
            }
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter email.")
                false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                showErrorSnackBar("Please enter a proper email.")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please enter password.")
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

        val intent: Intent = Intent(this, MainActivity::class.java)
        intent.putExtra("currentUser", auth.currentUser)
        startActivity(intent)
        // Finish the Sign-Up Screen
        finish()
    }
}