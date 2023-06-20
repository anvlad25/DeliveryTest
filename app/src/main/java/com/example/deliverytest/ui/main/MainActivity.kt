package com.example.deliverytest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.deliverytest.R
import com.example.deliverytest.routing.MainScreen
import com.example.deliverytest.routing.BagScreen
import com.example.deliverytest.routing.ProfileScreen
import com.example.deliverytest.routing.SearchScreen
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity() {
    private val cicerone: Cicerone<Router> = get()
    private val router = cicerone.router
    private val navigatorHolder = cicerone.getNavigatorHolder()
    private val navigator = AppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            router.newRootScreen(MainScreen)
        }

        val bottomMenu: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> router.newRootScreen(MainScreen)
                R.id.menu_search -> router.newRootScreen(SearchScreen)
                R.id.menu_bag -> router.newRootScreen(BagScreen)
                R.id.menu_profile -> router.newRootScreen(ProfileScreen)
            }
            true
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}