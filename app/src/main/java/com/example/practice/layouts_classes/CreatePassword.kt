package com.example.practice.layouts_classes

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.practice.PasswordInput
import com.example.practice.R

class CreatePassword : AppCompatActivity() {
    private var pref : SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_password)

    }

    fun password(view: View) {
        val circle1 = findViewById<ImageView>(R.id.passwordS1)
        val circle2 = findViewById<ImageView>(R.id.passwordS2)
        val circle3 = findViewById<ImageView>(R.id.passwordS3)
        val circle4 = findViewById<ImageView>(R.id.passwordS4)

        pref = getSharedPreferences("password", Context.MODE_PRIVATE)
        var CurrentPassword = pref?.getString("password","")!!
        CurrentPassword = PasswordInput().Add(view.id, CurrentPassword)

        if (CurrentPassword.length==4){
            val editor = pref?.edit()
            editor?.putString("password", "")
            editor?.apply()
            Toast.makeText(this,CurrentPassword,Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }else{
            val editor = pref?.edit()
            editor?.putString("password", CurrentPassword)
            editor?.apply()
        }
        if(CurrentPassword.isEmpty()){
            circle1.setImageResource(R.drawable.indicator_inac)
            circle2.setImageResource(R.drawable.indicator_inac)
            circle3.setImageResource(R.drawable.indicator_inac)
            circle4.setImageResource(R.drawable.indicator_inac)
        }else if(CurrentPassword.length==1){
            circle1.setImageResource(R.drawable.indicator_ac)
            circle2.setImageResource(R.drawable.indicator_inac)
            circle3.setImageResource(R.drawable.indicator_inac)
            circle4.setImageResource(R.drawable.indicator_inac)
        }else if(CurrentPassword.length==2){
            circle2.setImageResource(R.drawable.indicator_ac)
            circle3.setImageResource(R.drawable.indicator_inac)
            circle4.setImageResource(R.drawable.indicator_inac)
        }else if(CurrentPassword.length==3){
            circle3.setImageResource(R.drawable.indicator_ac)
            circle4.setImageResource(R.drawable.indicator_inac)
        }else if(CurrentPassword.length==4){
            circle4.setImageResource(R.drawable.indicator_ac)
        }

    }
    fun skip(view: View) {
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
        finish()
    }
}