package com.twoplusapp.twoplus.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.*
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

import com.sundin.twoplus.R
import com.twoplusapp.twoplus.database.FirestoreClass
import com.twoplusapp.twoplus.models.UserModel
import com.twoplusapp.twoplus.ui.home.HomeFragment
import com.twoplusapp.twoplus.ui.map.MapFragment
import com.twoplusapp.twoplus.ui.newPost.NewPostFragment
import com.twoplusapp.twoplus.ui.messages.MessagesFragment
import com.twoplusapp.twoplus.ui.profile.ProfileFragment
import com.twoplusapp.twoplus.utils.Constants


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    /**
     * A companion object to declare the constants.
     */
    companion object {
        //A unique code for starting the activity for result
        const val MY_PROFILE_REQUEST_CODE: Int = 11

        const val CREATE_BOARD_REQUEST_CODE: Int = 12
    }

    private lateinit var mSharedPreferences: SharedPreferences

    private var toolbarMainActivity: Toolbar? = null
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationDrawerView: NavigationView

    private val homeFragment: Fragment = HomeFragment()
    private val mapFragment: Fragment = MapFragment()
    private val newPostFragment: Fragment = NewPostFragment()
    private val messagesFragment: Fragment = MessagesFragment()
    private val profileFragment: Fragment = ProfileFragment()
    private val fm: FragmentManager = supportFragmentManager
    private lateinit var activeFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentUser = intent.extras

        setContentView(R.layout.activity_main)

        toolbarMainActivity = findViewById(R.id.toolbarMainActivity)
        setupActionBar()

        // Loading User Data into Drawer Navigation
        FirestoreClass().fetchUserData(this@MainActivity)

        drawerLayout = findViewById(R.id.drawerLayout)

        // Drawer Navigation View and listener
        navigationDrawerView = findViewById(R.id.navigationDrawerView)
        navigationDrawerView.setNavigationItemSelectedListener(this)

        mSharedPreferences =
            this.getSharedPreferences(Constants.BESO_PREFERENCES, Context.MODE_PRIVATE)

        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)

        if (savedInstanceState == null) {
            fm.commit {
                setReorderingAllowed(true)
                add<HomeFragment>(R.id.fragment_container_view)
                activeFragment = homeFragment
            }
        }

        // Variable is used get the value either token is updated in the database or not.
        val tokenUpdated = mSharedPreferences.getBoolean(Constants.FCM_TOKEN_UPDATED, false)

        // Here if the token is already updated than we don't need to update it every time.
        if (tokenUpdated) {
            // Get the current logged in user details.
            // Show the progress dialog.
            showProgressDialog(resources.getString(R.string.please_wait))
            // FirestoreClass().loadUserData(this@MainActivity)
        } else {
//            FirebaseInstallations.getInstance().
//                .addOnSuccessListener(this@MainActivity) { instanceIdResult ->
//                    updateFCMToken(instanceIdResult.token)
//                }
        }

        bottomNavView.setOnItemSelectedListener { item ->
            bottomNavViewOptions(item)
        }
    }

    // Navigation Drawer
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        Log.d("drawer", "Drawer item selected: ${R.id.navigationDrawerSignout.toString()}")
        when (menuItem.itemId) {
            R.id.navigationDrawerSettings -> {
                Log.d("settingsMenuItem", "Navigation Drawer Settings: ${R.id.navigationDrawerSettings.toString()}")
                val intentSettings: Intent = Intent(this, Settings::class.java)
                startActivity(intentSettings)
            }

            // TODO: get Drawer Signout functionality working
            R.id.navigationDrawerSignout -> {
                Log.d("signout", "Signout Button: ${R.id.navigationDrawerSignout.toString()}")
                // Here sign outs the user from firebase in this device.
                FirestoreClass().signOutUser()

                mSharedPreferences.edit().clear().apply()

                // Send the user to the intro screen of the application.
                val intent = Intent(this, IntroActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun bottomNavViewOptions(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.navigation_home -> {
                fm.commit {
                    setReorderingAllowed(true)
                    replace<HomeFragment>(R.id.fragment_container_view)
                    activeFragment = homeFragment
                }
                return true
            }
            R.id.navigation_map -> {
                fm.commit {
                    setReorderingAllowed(true)
                    replace<MapFragment>(R.id.fragment_container_view)
                    activeFragment = mapFragment
                }
                return true
            }
            R.id.navigation_new_post -> {
                fm.commit {
                    setReorderingAllowed(true)
                    replace<NewPostFragment>(R.id.fragment_container_view)
                    activeFragment = newPostFragment
                }
                return true
            }
            R.id.navigation_messages -> {
                fm.commit {
                    setReorderingAllowed(true)
                    replace<MessagesFragment>(R.id.fragment_container_view)
                    activeFragment = messagesFragment
                }
                return true
            }
            R.id.navigation_profile -> {
                fm.commit {
                    setReorderingAllowed(true)
                    replace<ProfileFragment>(R.id.fragment_container_view)
                    activeFragment = profileFragment
                }
                return true
            }
            else -> {
                return false
            }
        }
    }

    private fun toggleDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    fun loadUserProfileData(loggedInUser: UserModel?) {
//        hideProgressDialog()

        // Load the user image into the Main Activity Drawer
        Glide
            .with(this@MainActivity)
            .load(loggedInUser?.userProfileImage)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_account_circle_24)
            .into(findViewById(R.id.ivProfileIcon));
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbarMainActivity)
        toolbarMainActivity?.setNavigationIcon(R.drawable.ic_baseline_menu_24)
        toolbarMainActivity?.setNavigationOnClickListener {
            toggleDrawer()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK
            && requestCode == MY_PROFILE_REQUEST_CODE
        ) {
            // Get the user updated details.
            FirestoreClass().fetchUserData(this@MainActivity)
        } else if (resultCode == Activity.RESULT_OK
            && requestCode == CREATE_BOARD_REQUEST_CODE
        ) {
            // Get the latest boards list.
//            FirestoreDB().getBoardsList(this@MainActivity)
        } else {
            Log.e("Cancelled", "Cancelled")
        }
    }

    /**
     * A function to notify the token is updated successfully in the database.
     */
    fun tokenUpdateSuccess() {

        hideProgressDialog()

        // Here we have added a another value in shared preference that the token is updated in the database successfully.
        // So we don't need to update it every time.
        val editor: SharedPreferences.Editor = mSharedPreferences.edit()
        editor.putBoolean(Constants.FCM_TOKEN_UPDATED, true)
        editor.apply()

        // Get the current logged in user details.
        // Show the progress dialog.
        showProgressDialog(resources.getString(R.string.please_wait))
        FirestoreClass().fetchUserData(this@MainActivity)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            // A double back press function is added in Base Activity.
            doubleBackToExit()
        }
    }

}