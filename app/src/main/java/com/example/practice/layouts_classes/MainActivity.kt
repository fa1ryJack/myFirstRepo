package com.example.practice.layouts_classes

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.practice.R
import com.example.retrolab.retrofit.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var email1 = findViewById<EditText>(R.id.Email1)
        var next1 = findViewById<Button>(R.id.next1)
        next1.isClickable=false

        email1.addTextChangedListener(object : TextWatcher
        {
            override fun afterTextChanged(p0: Editable?) {
                if ("@" in email1.text.toString()){

                    next1.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.butt_av))
                    next1.isClickable=true
                }
                else{
                    next1.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.butt_not_av))
                    next1.isClickable=false
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

    }

    fun next1(view: View) {

        var emailIn = findViewById<EditText>(R.id.Email1).text.toString()

        val inter = HttpLoggingInterceptor()
        inter.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(inter).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://medic.madskill.ru").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val mainAPI = retrofit.create(API::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val Message = mainAPI.goEmail(
                emailIn
            )

            runOnUiThread {
                var intent = Intent(this@MainActivity, EmailCodeCheck::class.java)
                intent.putExtra("email", emailIn)
                startActivity(intent)
            }
        }


    }


}