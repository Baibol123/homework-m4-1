package com.example.homework_m4_1

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.homework_m4_1.databinding.ActivityMainBinding
import com.example.homework_m4_1.ui.onboarding.OnbordingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val onbordingFragment = OnbordingFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{ controller, destination, args ->
            when (destination.id){
                R.id.splashFragment -> binding.navView.visibility = View.GONE
                R.id.onViewPagerFragment -> binding.navView.visibility = View.GONE
                else -> binding.navView.visibility = View.VISIBLE

            }

        }
    }
}