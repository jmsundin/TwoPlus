package com.twoplusapp.twoplus.activities

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.twoplusapp.twoplus.R
import com.twoplusapp.twoplus.database.FirestoreClass
import com.twoplusapp.twoplus.databinding.ActivitySettingsBinding
import com.twoplusapp.twoplus.models.UserModel

class SettingsActivity: BaseActivity() {
    lateinit var settingsBinding: ActivitySettingsBinding
    lateinit var settingsPersonName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

//        settingsBinding = ActivitySettingsBinding.inflate(layoutInflater)

        settingsPersonName = findViewById(R.id.settingsPersonName)

        val btnDeleteAccount: Button = findViewById(R.id.btnDeleteAccount)
        btnDeleteAccount.setOnClickListener{
//            Log.d("btnDeleteAccount", "button is working")
            FirestoreClass().deleteAccount(this)
        }
    }
    fun loadUserData(loggedInUser: UserModel?){
//        Log.d("Settings.loadUserData", "outside let function")
        loggedInUser?.userFirstName?.let { Log.d("SettingsName", it) }
        val userFirstName: String? = loggedInUser?.userFirstName
        val userLastName: String? = loggedInUser?.userLastName
        val name = "$userFirstName $userLastName"
        settingsPersonName.text = name
    }
}