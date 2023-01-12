package com.example.newsapp_v1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.newsapp_v1.R
import com.example.newsapp_v1.databinding.ActivityMainBinding
import com.example.newsapp_v1.ui.fragments.BreakingNewsFragment
import com.example.newsapp_v1.ui.fragments.SavedNewsFragment
import com.example.newsapp_v1.ui.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView



class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        bottomNav = binding.bottomNavView as BottomNavigationView

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.breakingNewsFragment -> {
                    loadFragment(BreakingNewsFragment())
                    true
                }
                R.id.savedNewsFragment -> {
                    loadFragment(SavedNewsFragment())
                    true
                }

                R.id.searchNewsFragment -> {
                    loadFragment(SearchFragment())
                    true
                }

                else -> {false}
            }

        }

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.commit()
    }
}