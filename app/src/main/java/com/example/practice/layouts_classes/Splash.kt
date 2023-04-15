package com.example.practice.layouts_classes

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practice.R
import java.util.*
import kotlin.concurrent.schedule

class Splash : AppCompatActivity() {
    var pref : SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        pref = getSharedPreferences("flags", Context.MODE_PRIVATE)
        var CurrentFlag = pref?.getString("board","")!!



        if (CurrentFlag==""){var intent = Intent(this, Start::class.java)
        Timer("timer", false).schedule(2000){
            startActivity(intent)
            finish()
        }}else if (CurrentFlag=="done"){var intent = Intent(this, Main::class.java)
            Timer("timer", false).schedule(2000){
                startActivity(intent)
                finish()
            }
        }
    }
}