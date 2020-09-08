package com.example.gadsleaderboard

import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

// ViewPager Adapter. This is used to populate info for the viewPager used in MainActivity
// It extends FragmentStateAdapter and overrides 3 methods. getPageTitle, getItem and getCount.
class PagerAdapter(fm: FragmentManager?, tabCount: Int) : FragmentStatePagerAdapter(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private val tabTitles = arrayOf("Learning Leaders", "Skill IQ Leaders")

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null

        if (position == 0) {
            fragment = LearnersFragment()
        } else if (position == 1) {
            fragment = SkillFragment()
        }
        return fragment!!
    }

    override fun getCount(): Int {
        return tabTitles.size
    }

}