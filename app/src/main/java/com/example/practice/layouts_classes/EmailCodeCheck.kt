package com.example.practice.layouts_classes

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

class EmailCodeCheck : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_code_check)
        val codeField = findViewById<EditText>(R.id.CODE)

        val inter = HttpLoggingInterceptor()
        inter.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(inter).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://medic.madskill.ru").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val mainAPI = retrofit.create(API::class.java)

        codeField.addTextChangedListener(object : TextWatcher{

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            @SuppressLint("SuspiciousIndentation")
            override fun afterTextChanged(p0: Editable?) {
                val code = codeField.text.toString()
                val email = intent.getStringExtra("email").toString()
                if(code.length==4){
                CoroutineScope(Dispatchers.IO).launch {
                    val token = mainAPI.goIn(
                        email,
                        code
                    ).token

                    runOnUiThread {
                        val intent = Intent(this@EmailCodeCheck, CreatePassword::class.java)
                      var pref = getSharedPreferences("token", Context.MODE_PRIVATE)
                        pref.edit().putString("token", token).apply()
                        startActivity(intent)
                    }
                }
                }
            }

        })
    }
}