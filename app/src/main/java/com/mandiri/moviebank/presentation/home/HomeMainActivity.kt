package com.mandiri.moviebank.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mandiri.moviebank.R
import com.mandiri.moviebank.databinding.HomeMainActivityBinding
import com.mandiri.moviebank.presentation.bookmark.BookmarkFragment
import com.mandiri.moviebank.presentation.profile.ProfileFragment

class HomeMainActivity : AppCompatActivity() {
    private lateinit var binding: HomeMainActivityBinding

    private val onNavigationSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationHome -> {
                    replaceFragment(HomeFragment(::replaceFragment))
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigationBookmark -> {
                    replaceFragment(BookmarkFragment())
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigationProfile -> {
                    replaceFragment(ProfileFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeMainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnNavigationItemSelectedListener(onNavigationSelectedListener)

        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.navigationHome
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

}