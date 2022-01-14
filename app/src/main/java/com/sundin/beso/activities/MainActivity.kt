package com.sundin.beso.activities

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.sundin.beso.MainAdapter
import com.sundin.beso.R
import com.sundin.beso.databinding.ActivityMainBinding
import com.sundin.beso.databinding.FragmentHomeBinding
import com.sundin.beso.utils.Constants

class MainActivity : BaseActivity(), OnMapReadyCallback {

    private lateinit var bindingActivityMain: ActivityMainBinding
    private lateinit var bindingFragmentHome: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingActivityMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingActivityMain.root)

        val navView: BottomNavigationView = bindingActivityMain.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_map, R.id.navigation_create,
                R.id.navigation_messages, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        // bind the recyclerview from the home fragment
        val rvThings = bindingFragmentHome.rvThings
        // get sample things list of data
        val thingsList = Constants.thingsList
        // Create adapter passing in the sample user data
        val adapterThings = MainAdapter(this, thingsList)
        // Attach the adapter to the recyclerview to populate items
        rvThings.adapter = adapterThings
        // Set layout manager to position the items
        rvThings.layoutManager = LinearLayoutManager(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")

    }
}