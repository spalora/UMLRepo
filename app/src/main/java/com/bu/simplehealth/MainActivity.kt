package com.bu.simplehealth

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

/**
 * @author Sandeep Agrawal
 * This activity is the base activity for the app
 */
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.nav_graph)
        val navController = navHostFragment.navController

        val loggedInUser = SharePreferenceData.getSharedPrefString(this, "user", "")
        val destination = if (loggedInUser?.isNotEmpty() == true) R.id.homeFragment else R.id.loginSignupFragment
        navGraph.startDestination = destination
        navController.graph = navGraph
        init()
    }

    private fun init() {
        setupActionBar()
        setTitleVisibility(View.GONE)
        //TODO:Click listener
    }


}