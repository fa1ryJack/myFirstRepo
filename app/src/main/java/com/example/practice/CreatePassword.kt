package com.example.practice

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class CreatePassword : AppCompatActivity() {
    private var pref : SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_password)
    }

    fun password(view: View) {
        pref = getSharedPreferences("password", Context.MODE_PRIVATE)
        var CurrentPassword = pref?.getString("password","")!!
        CurrentPassword = PasswordInput().Add(view.id, CurrentPassword)
        if (CurrentPassword.length==4){
            val editor = pref?.edit()
            editor?.putString("password", "")
            editor?.apply()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            val editor = pref?.edit()
            editor?.putString("password", CurrentPassword)
            editor?.apply()
        }
    }
    fun skip(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}