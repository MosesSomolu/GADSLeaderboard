package com.example.gadsleaderboard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.gadsleaderboard.R

class SubmitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)
        //show Back Button
        assert(supportActionBar != null)
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        viewRefs()
    }

    private fun viewRefs(){
            findViewById<Button>(R.id.submitRepo)
    }

    fun submitRepo(view : View){
        viewRefs()
        Log.i("SUBMITREPO", "The Repo has been Submitted!")
    }

    //initiate BackButton
    override fun onSupportNavigateUp(): Boolean {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        return true
    }


}
