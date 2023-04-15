package com.example.practice

import android.content.Context
import android.content.res.ColorStateList
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.practice.layouts_classes.MainActivity2

class TextWatchMain2(private val context: Context)
{
    var name1 = (context as MainActivity2).findViewById<EditText>(R.id.et2)
    var fname1 = (context as MainActivity2).findViewById<EditText>(R.id.et4)
    var surname1 = (context as MainActivity2).findViewById<EditText>(R.id.et5)
    var birthdate1 = (context as MainActivity2).findViewById<EditText>(R.id.et6)
    var pol1 = (context as MainActivity2).findViewById<Spinner>(R.id.gender)
    var create = (context as MainActivity2).findViewById<Button>(R.id.create)
    fun Button_av(){
        if ((name1.text.length < 4) || (fname1.text.length < 4) || (surname1.text.length < 4) || (birthdate1.text.length != 10)  || (pol1.selectedItem == "Пол")){
            create.backgroundTintList = ColorStateList.valueOf((context as MainActivity2).resources.getColor(R.color.butt_not_av))
            create.isClickable=false
        }
        else{
            create.backgroundTintList = ColorStateList.valueOf((context as MainActivity2).resources.getColor(R.color.butt_av))
            create.isClickable=true
        }
    }
}