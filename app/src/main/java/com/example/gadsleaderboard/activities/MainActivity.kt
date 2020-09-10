package com.example.gadsleaderboard.activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.viewpager.widget.ViewPager
import com.example.gadsleaderboard.*
import com.example.gadsleaderboard.adapters.LearnersAdapter
import com.example.gadsleaderboard.adapters.PagerAdapter
import com.example.gadsleaderboard.adapters.SkillAdapter
import com.example.gadsleaderboard.fragments.LearnersFragment
import com.example.gadsleaderboard.fragments.SkillFragment
import com.example.gadsleaderboard.models.RequestResult
import com.example.gadsleaderboard.retroTools.DataRetriever
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_leaders.*
import kotlinx.android.synthetic.main.fragment_skill.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    var tabLayout: TabLayout? = null
    private val dr = DataRetriever()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTheme(R.style.AppTheme)


        viewRefs()
        setTabDetails()
        setUpAdapter()

        if (isNetworkConnected()) {
            getHours()
            getSkill()
        }
        else {
            AlertDialog.Builder(this@MainActivity).setTitle("No Internet Connection")
                .setMessage("Please check your internet connection and try again")
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }


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


    //ID references
    fun viewRefs() {
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        findViewById<Button>(R.id.submitButton)
        findViewById<Button>(R.id.submitRepo)
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
    fun startActivity(view : View) {
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

    //function to get data on top hour learners using kotlin coroutines
    private fun getHours() {
        //1 Create a Coroutine scope using a job to be able to cancel when needed
        val mainActivityJob = Job()

        //2 Handle exceptions if any
        val errorHandler = CoroutineExceptionHandler { _, exception ->
            AlertDialog.Builder(this).setTitle("Error")
                .setMessage(exception.message)
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }

        //3 the Coroutine runs using the Main (UI) dispatcher
        val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)
        coroutineScope.launch(errorHandler) {
            //4
            val result = DataRetriever().getHours()
            Log.i("HOURSRESULTS!!!", result.toString())
            leadersRecyclerView.adapter = LearnersAdapter(this@MainActivity,result)
        }
    }

    //function to get the data on top skill iq learners using kotlin coroutines
    private fun getSkill(){
        //1 Create a Coroutine scope using a job to be able to cancel when needed
        val mainActivityJob = Job()

        //2 Handle exceptions if any
        val errorHandler = CoroutineExceptionHandler { _, exception ->
            AlertDialog.Builder(this).setTitle("Error")
                .setMessage(exception.message)
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }

        //3 the Coroutine runs using the Main (UI) dispatcher
        val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)
        coroutineScope.launch(errorHandler) {
            //4
            val result = DataRetriever().getSkill()
            Log.i("SKILLRESULT!!!!", result.toString())
            skillRecyclerView.adapter = SkillAdapter(this@MainActivity,result)
        }
    }

    //Method to check if the users device is connected to the internet
    @RequiresApi(Build.VERSION_CODES.M)
    private fun isNetworkConnected(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}
