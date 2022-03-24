package com.twoplusapp.twoplus.activities

import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.sundin.twoplus.R
import com.twoplusapp.twoplus.database.FirestoreClass
import com.sundin.twoplus.databinding.ActivitySettingsBinding

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