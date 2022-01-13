package com.sundin.beso.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.sundin.beso.R

import com.sundin.beso.databinding.ActivityIntroBinding

class IntroActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_intro)

        val binding = ActivityIntroBinding.inflate(layoutInflater)

        val btnSignIn = binding.btnSignIn
        btnSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }


        val btnSignUp = binding.btnSignUp
        // TODO: create Sign-Up Activity Screen
        // TODO: Add intent and startActivity code once Sign-Up Activity is made

    }

}