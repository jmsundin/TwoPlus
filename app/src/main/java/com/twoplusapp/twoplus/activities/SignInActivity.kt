package com.twoplusapp.twoplus.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.twoplusapp.twoplus.R
import com.twoplusapp.twoplus.databinding.ActivitySignInBinding
import com.twoplusapp.twoplus.models.UserModel

//import kotlinx.android.synthetic.main.activity_login.*


class SignInActivity : BaseActivity(){

    lateinit var bindingSignInScreen: ActivitySignInBinding

    private companion object SignInActivity {
        private const val TAG = "SignInActivity"
        private const val RC_GOOGLE_SIGN_IN = 4926
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var gmailSignInButton: Button

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // Handle the returned Uri
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        bindingSignInScreen = ActivitySignInBinding.inflate(layoutInflater)

        setupActionBar()

        // Initialize Firebase Auth
        auth = Firebase.auth

        val signInSubmitBtn: Button = findViewById(R.id.btnSignInSubmit)
        signInSubmitBtn.setOnClickListener {
            signInRegisteredUser()
        }

//        val gso =
//            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build()
//        val googleSignInClient = GoogleSignIn.getClient(this, gso)
//
//        gmailSignInButton = findViewById(R.id.gmail_sign_in_button)
//        gmailSignInButton.setOnClickListener {
//            val signInIntent = googleSignInClient.signInIntent
//
//            startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN)
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }


    private fun signInRegisteredUser(){
        val etEmail: EditText = findViewById(R.id.etEmailSignIn)
        val etPassword: EditText = findViewById(R.id.etPasswordSignIn)

        val email: String = etEmail.text.toString().trim { it <= ' ' }
        val password: String = etPassword.text.toString().trim { it <= ' ' }

        if(validateForm(email, password)){
            showProgressDialog("Please wait, signing you in")
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    hideProgressDialog()
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }
    }


    private fun validateForm(email: String, password: String): Boolean {
        return when {
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


    private fun updateUI(user: FirebaseUser?) {
        if (user == null) {
            Log.w(TAG, "user not signed in..")
            return
        }
        val intent: Intent = Intent(this, MainActivity::class.java)
        intent.putExtra("currentUser", auth.currentUser)
        startActivity(intent)
        finish()
        // Navigate to MainActivity
    }


    fun signInSuccess(user: UserModel?){
        hideProgressDialog()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    //    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_GOOGLE_SIGN_IN) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                // Google Sign In was successful, authenticate with Firebase
//                val account = task.getResult(ApiException::class.java)!!
//                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
//                firebaseAuthWithGoogle(account.idToken!!)
//            } catch (e: ApiException) {
//                // Google Sign In failed, update UI appropriately
//                Log.w(TAG, "Google sign in failed", e)
//            }
//        }
//    }


    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }


    private fun setupActionBar() {

        val toolbarSignInActivity: androidx.appcompat.widget.Toolbar?
            = findViewById(R.id.toolbarSignInActivity)

        toolbarSignInActivity?.setOnClickListener {
            onBackPressed()
        }
    }
}