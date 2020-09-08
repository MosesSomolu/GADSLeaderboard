package com.example.gadsleaderboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    var tabLayout: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewRefs()
        setTabDetails()
        setUpAdapter()

        //Initiate LeadersFragment
        if (savedInstanceState == null) {
            // 2
            supportFragmentManager
                // 3
                .beginTransaction()
                // 4
                .add(R.id.viewPager, LearnersFragment.newInstance(), "LearnersFragment")
                // 5
                .commit()
        }

        //Initiate SkillFragment
        if (savedInstanceState == null) {
            // 2
            supportFragmentManager
                // 3
                .beginTransaction()
                // 4
                .add(R.id.viewPager, SkillFragment.newInstance(), "SkillFragment")
                // 5
                .commit()
        }

    }

    //Methods

    //ID references
    private fun viewRefs() {
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        val button = findViewById<Button>(R.id.submitButton)
    }

    //Function to link viewPager Adapter with MainActivity and initialize click listeners for viewPager and TabLayout
    private fun setUpAdapter(){
        val pagerAdapter = PagerAdapter(supportFragmentManager, tabLayout!!.tabCount)
        viewPager.adapter = pagerAdapter
        initializeVPChangeListener()
        initializeTabChangeListener()
    }

    //Function to launch SubmitActivity
    fun startActivity(view: View){
        viewRefs()
        val intent = Intent(this, SubmitActivity::class.java)
        startActivity(intent)
    }

    //Function to set Titles for the Tabs
    private fun setTabDetails(){
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Learning Leaders"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Skill IQ Leaders"))
//        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
    }

    //Function to initialize change listener for ViewPager
    private fun initializeVPChangeListener(){
        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
    }

    //Function to initialize Tab change listener
    private fun initializeTabChangeListener(){
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
            }
            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }

}
