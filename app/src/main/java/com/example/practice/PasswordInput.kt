package com.example.practice

import android.content.Context
import android.content.SharedPreferences
import android.widget.Button

class PasswordInput {


   fun Add(butt_id: Int, password: String): String {
var password = password
       if (password.length == 4){
           return password
       }
       else{
       when (butt_id){
            R.id.pass1 ->{password += "1"}
            R.id.pass2 ->{password += "2"}
            R.id.pass4 ->{password += "4"}
            R.id.pass5 ->{password += "5"}
            R.id.pass6 ->{password += "6"}
            R.id.pass7 ->{password += "7"}
            R.id.pass8 ->{password += "8"}
            R.id.pass9 ->{password += "9"}
            R.id.pass0 ->{password += "0"}
            R.id.pass3 ->{password += "3"}
           R.id.passD ->{if (!password.isEmpty()){password = password.substring(0, password.length-1)}}
        }
           return  password
    }}
}