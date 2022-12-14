package com.D121201081.taskoo.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.D121201081.taskoo.R
import com.D121201081.taskoo.adapter.IntroAdapter
import com.D121201081.taskoo.databinding.ActivityIntroBinding
import com.D121201081.taskoo.utils.Animasi
import com.google.android.material.tabs.TabLayoutMediator

class IntroActivity : AppCompatActivity() {

    private lateinit var binding:ActivityIntroBinding

    private lateinit var mViewPager: ViewPager2
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        mViewPager = binding.viewPager
        mViewPager.adapter = IntroAdapter(this, this)
        TabLayoutMediator(binding.pageIndicator, mViewPager) { _, _ -> }.attach()

        val btnNextStep: Button = findViewById(R.id.btn_get_started)

        btnNextStep.setOnClickListener {
            if (getItem() > mViewPager.childCount) {
                setIntroFinish()
                finish()
                val intent =
                    Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                Animasi.animateSlideLeft(this)
            } else {
                mViewPager.setCurrentItem(getItem() + 1, true)
            }
        }
    }

    private fun setIntroFinish(){
        val editor:SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("FinishIntro?",true)
        editor.apply()
    }

    private fun getItem(): Int {
        return mViewPager.currentItem
    }
}
