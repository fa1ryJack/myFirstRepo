package com.example.practice

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
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


class MainActivity2 : AppCompatActivity() {
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
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        pol1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                TextWatchMain2(this@MainActivity2).Button_av()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }



    }




    fun create(view: View) {
        var intent = Intent(this, Main::class.java)
        startActivity(intent)
        finish()
    }
}