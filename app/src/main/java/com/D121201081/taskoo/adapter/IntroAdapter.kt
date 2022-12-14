package com.D121201081.taskoo.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.D121201081.taskoo.R
import com.D121201081.taskoo.fragment.IntroFragment

class IntroAdapter (fragmentActivity: FragmentActivity, private val context: Context):
    FragmentStateAdapter(fragmentActivity){
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> IntroFragment.newInstance(
                context.resources.getString(R.string.onboard_judul1),
                context.resources.getString(R.string.onboard_desk1),
                R.raw.image_onboarding1
            )
            1 -> IntroFragment.newInstance(
                context.resources.getString(R.string.onboard_judul2),
                context.resources.getString(R.string.onboard_desk2),
                R.raw.image_onboarding2
            )
            else -> IntroFragment.newInstance(
                context.resources.getString(R.string.onboard_judul3),
                context.resources.getString(R.string.onboard_desk3),
                R.raw.image_onboarding3
            )
        }
    }
    override fun getItemCount(): Int {
        return 3
    }
}