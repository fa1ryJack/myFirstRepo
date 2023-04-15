package com.example.practice.layouts_classes

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.practice.R
import com.example.practice.TextWatchMain2
import com.example.practice.retrofit.Profile
import com.example.retrolab.retrofit.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity2 : AppCompatActivity() {

    lateinit var name: String
    lateinit var fname: String
    lateinit var sname: String
    lateinit var birth: String
    lateinit var pol: String


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var name1 = findViewById<EditText>(R.id.et2)
        var fname1 = findViewById<EditText>(R.id.et4)
        var surname1 = findViewById<EditText>(R.id.et5)
        var birthdate1 = findViewById<EditText>(R.id.et6)
        var pol1 = findViewById<Spinner>(R.id.gender)
        val gender = arrayOf("Пол","Мужской","Женский")
        val adapter = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, gender)
        pol1.adapter = adapter
        var create = findViewById<Button>(R.id.create)
        create.isClickable=false




        name1.addTextChangedListener(object : TextWatcher
        {
            override fun afterTextChanged(p0: Editable?) {
                TextWatchMain2(this@MainActivity2).Button_av()
                name = name1.text.toString()
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })


        surname1.addTextChangedListener(object : TextWatcher
        {
            override fun afterTextChanged(p0: Editable?) {
                TextWatchMain2(this@MainActivity2).Button_av()
                sname = surname1.text.toString()
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        birthdate1.addTextChangedListener(object : TextWatcher
        {
            override fun afterTextChanged(p0: Editable?) {
                TextWatchMain2(this@MainActivity2).Button_av()
                birth=birthdate1.text.toString()
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        fname1.addTextChangedListener(object : TextWatcher
        {
            override fun afterTextChanged(p0: Editable?) {
                TextWatchMain2(this@MainActivity2).Button_av()
                fname=fname1.text.toString()
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        pol1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                TextWatchMain2(this@MainActivity2).Button_av()
                pol=pol1.selectedItem.toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }



    }




    fun create(view: View) {

        val inter = HttpLoggingInterceptor()
        inter.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(inter).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://medic.madskill.ru").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val mainAPI = retrofit.create(API::class.java)
       val pref = getSharedPreferences("token", Context.MODE_PRIVATE)
        val token = pref.getString("token","")
        CoroutineScope(Dispatchers.IO).launch {
            val Prof = mainAPI.createProfile("Bearer "+token.toString(), Profile(11111, name, fname, sname, birth, pol, "0"))
            runOnUiThread {
                var intent = Intent(this@MainActivity2, Main::class.java)
                startActivity(intent)
                finish()
            }
        }

    }

    fun skip(view: View) {var intent = Intent(this@MainActivity2, Main::class.java)
        startActivity(intent)
        finish()}
}