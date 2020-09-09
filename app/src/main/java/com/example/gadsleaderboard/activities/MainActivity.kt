package com.example.gadsleaderboard.activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.viewpager.widget.ViewPager
import com.example.gadsleaderboard.*
import com.example.gadsleaderboard.adapters.LearnersAdapter
import com.example.gadsleaderboard.adapters.PagerAdapter
import com.example.gadsleaderboard.fragments.LearnersFragment
import com.example.gadsleaderboard.fragments.SkillFragment
import com.example.gadsleaderboard.models.RequestResult
import com.example.gadsleaderboard.retroTools.Request
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_leaders.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    var tabLayout: TabLayout? = null
    //private val dr = DataRetriever()

    //2
    private val callback = object : Callback<RequestResult> {
        override fun onFailure(call: Call<RequestResult>?, t: Throwable?) {
            Log.e("MainActivity", "Problem calling GADS API {${t?.message}}")
        }


        override fun onResponse(call: Call<RequestResult>?, response: Response<RequestResult>?) {

            response?.isSuccessful.let {
                val result = RequestResult(response?.body()?.users ?: emptyList())
                leadersRecyclerView.adapter = LearnersAdapter(result)
            }


        }

        @RequiresApi(Build.VERSION_CODES.M)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            viewRefs()
            setTabDetails()
            setUpAdapter()

            requestLink()

            //Initiate LeadersFragment
            if (savedInstanceState == null) {
                supportFragmentManager
                    .beginTransaction()
                    .add(
                        R.id.viewPager,
                        LearnersFragment.newInstance(), "LearnersFragment"
                    )
                    .commit()
            }

            //Initiate SkillFragment
            if (savedInstanceState == null) {
                supportFragmentManager
                    .beginTransaction()
                    .add(
                        R.id.viewPager,
                        SkillFragment.newInstance(), "SkillFragment"
                    )
                    .commit()
            }

        }

        //Methods

        //ID references
        private fun viewRefs() {
            viewPager = findViewById(R.id.viewPager)
            tabLayout = findViewById(R.id.tabLayout)
            findViewById<Button>(R.id.submitButton)
        }

        //Function to link viewPager Adapter with MainActivity and initialize click listeners for viewPager and TabLayout
        private fun setUpAdapter() {
            val pagerAdapter = PagerAdapter(
                supportFragmentManager
            )
            viewPager.adapter = pagerAdapter
            initializeVPChangeListener()
            initializeTabChangeListener()
        }

        //Function to launch SubmitActivity
        fun startActivity() {
            viewRefs()
            val intent = Intent(this, SubmitActivity::class.java)
            startActivity(intent)
        }

        //Function to set Titles for the Tabs
        private fun setTabDetails() {
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Learning Leaders"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Skill IQ Leaders"))
//        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
        }

        //Function to initialize change listener for ViewPager
        private fun initializeVPChangeListener() {
            viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        }

        //Function to initialize Tab change listener
        private fun initializeTabChangeListener() {
            tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewPager.currentItem = tab.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                }
            })
        }

        //Method to make a network request and display an Alert Dialog
        @RequiresApi(Build.VERSION_CODES.M)
        fun requestLink() {
            val url = "https://gadsapi.herokuapp.com"
            if (isNetworkConnected()) {
                doAsync {
                    Request(url).run()
                    uiThread { longToast("Request performed") }
                }
            } else {
                AlertDialog.Builder(this).setTitle("No Internet Connection")
                    .setMessage("Please check your internet connection and try again")
                    .setPositiveButton(android.R.string.ok) { _, _ -> }
                    .setIcon(android.R.drawable.ic_dialog_alert).show()
            }

        }

        //Method to check if the users device is connected to the internet
        @RequiresApi(Build.VERSION_CODES.M)
        private fun isNetworkConnected(): Boolean {
            //1
            val connectivityManager =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            //2
            val activeNetwork = connectivityManager.activeNetwork
            //3
            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            //4
            return networkCapabilities != null &&
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }
    }

}
