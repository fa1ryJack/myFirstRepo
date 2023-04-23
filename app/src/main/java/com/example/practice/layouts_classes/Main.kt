package com.example.practice.layouts_classes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.CatalogAdapter
import com.example.practice.R
import com.example.retrolab.retrofit.API
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Main : AppCompatActivity() {

    lateinit var catalogAdapter: CatalogAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val inter = HttpLoggingInterceptor()
        inter.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(inter).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://medic.madskill.ru").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val mainAPI = retrofit.create(API::class.java)

      initAdapter()

        CoroutineScope(Dispatchers.IO).launch {
            val responce = mainAPI.getCatalog()
            runOnUiThread {
                catalogAdapter.differ.submitList(responce.body())
            }
        }

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

    private fun initAdapter() {
        catalogAdapter = CatalogAdapter()
        findViewById<RecyclerView>(R.id.catalog_adapter).apply {
            adapter = catalogAdapter
            layoutManager = LinearLayoutManager(this@Main)
        }
    }
}


