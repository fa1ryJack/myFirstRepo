package com.example.practice.layouts_classes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practice.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val OnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
                item -> when(item.itemId){
            R.id.analysis -> {
                startActivity(Intent(this, Main::class.java))
                return@OnNavigationItemSelectedListener true
            }
            R.id.results -> {
                startActivity(Intent(this, Results::class.java))
                return@OnNavigationItemSelectedListener true
            }
            R.id.support -> {
                startActivity(Intent(this, Support::class.java))
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile -> {
                startActivity(Intent(this, Profile::class.java))
                return@OnNavigationItemSelectedListener true
            }
        }
            false
        }
        findViewById<BottomNavigationView>(R.id.menu).setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener)
    }
}