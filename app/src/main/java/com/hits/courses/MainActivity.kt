package com.hits.courses

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hits.feature.main.databinding.ActivityMainBinding
import com.hits.feature.main.ui.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        bottomNavigation = binding.bottomNavigation

        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(com.hits.feature.main.R.id.fragment_container, MainFragment())
                .commit()
        }

        bottomNavigation.setOnItemSelectedListener { item ->
            val selectedFragment = when(item.itemId) {
                com.hits.feature.main.R.id.nav_main -> MainFragment()
                //com.hits.feature.main.R.id.nav_favorites -> FavoritesFragment()
                //com.hits.feature.main.R.id.nav_account -> AccountFragment()
                else -> MainFragment()
            }

            supportFragmentManager.beginTransaction()
                .replace(com.hits.feature.main.R.id.fragment_container, selectedFragment)
                .commit()

            true
        }
    }
}