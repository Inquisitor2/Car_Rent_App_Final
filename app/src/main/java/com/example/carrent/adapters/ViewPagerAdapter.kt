package com.example.carrent.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fa : FragmentActivity, var fList : List<Fragment>)
    : FragmentStateAdapter(fa) {

    override fun getItemCount() = fList.size

    override fun createFragment(position: Int): Fragment {
        return fList[position]
    }
}