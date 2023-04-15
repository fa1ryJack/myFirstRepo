package com.example.practice.layouts_classes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.CatalogAdapter
import com.example.practice.R
import com.example.retrolab.retrofit.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Main : AppCompatActivity() {
    //private val viewModel by viewModels<MainViewModel>()
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
    }

    private fun initAdapter() {
        catalogAdapter = CatalogAdapter()
        findViewById<RecyclerView>(R.id.catalog_adapter).apply {
            adapter = catalogAdapter
            layoutManager = LinearLayoutManager(this@Main)
        }
    }
}


