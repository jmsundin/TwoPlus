package com.sundin.beso.activities

import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.sundin.beso.R
import com.sundin.beso.database.FirestoreClass
import com.sundin.beso.databinding.ActivitySettingsBinding

class Settings: BaseActivity() {
    lateinit var settingsBinding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        settingsBinding = ActivitySettingsBinding.inflate(layoutInflater)

        val btnDeleteAccount: Button = findViewById(R.id.btnDeleteAccount)
        btnDeleteAccount.setOnClickListener{
            Log.d("btnDeleteAccount", "button is working")
            FirestoreClass().deleteAccount(this)
        }
    }
}