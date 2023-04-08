package com.example.practice

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.onboarding.OnboardingItem
import com.example.onboarding.OnboardingItemsAdapter

class Start : AppCompatActivity() {
    private var pref : SharedPreferences? = null
    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter

    private lateinit var indicatorsContainer: LinearLayout

    private lateinit var skipText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        pref = getSharedPreferences("flags", Context.MODE_PRIVATE)


        setOnboardingItems()
        setupIndicators()
        setCurrentIndicator(0)
    }

    private fun setOnboardingItems(){
        onboardingItemsAdapter = OnboardingItemsAdapter(
            listOf(
                OnboardingItem(
                    title = "Анализы",
                    description = "Экспресс сбор и получение проб",
                    onboardingImage = R.drawable.on1
                ),
                OnboardingItem(
                    title = "Уведомления",
                    description = "Вы быстро узнаете о результатах",
                    onboardingImage = R.drawable.on2
                ),
                OnboardingItem(
                    title = "Мониторинг",
                    description = "Наши врачи всегда наблюдают \n" +
                            "за вашими показателями здоровья",
                    onboardingImage = R.drawable.on3
                )
            )
        )
        val onboardingItemViewPager = findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardingItemViewPager.adapter = onboardingItemsAdapter

        onboardingItemViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (onboardingItemViewPager.getChildAt(0) as RecyclerView).overScrollMode=
            RecyclerView.OVER_SCROLL_NEVER
    }

    private fun setupIndicators(){
        indicatorsContainer = findViewById(R.id.indicatorsContainer)
        skipText = findViewById(R.id.Skiptext)

        val indicators = arrayOfNulls<ImageView>(onboardingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let{
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inac
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }
    private fun setCurrentIndicator(position: Int){
        val childCount = indicatorsContainer.childCount
        for(i in 0 until childCount){
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if (position == 2){
                skipText.text = "Завершить"
            }else{
                skipText.text="Пропустить"}
            if(i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_ac
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inac))
            }
        }
    }

    fun skip() {

        val editor = pref?.edit()
        editor?.putString("board", "done")
        editor?.apply()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}