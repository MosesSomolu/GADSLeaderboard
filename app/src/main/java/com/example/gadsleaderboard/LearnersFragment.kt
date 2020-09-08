package com.example.gadsleaderboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// Fragment linked to MainActivity and Viewpager to display data on the Top learners
class LearnersFragment : Fragment() {

    //This is created to enable the Fragment Manager called in MainActivity to create the newInstance
    //of LearnersFragment so that it can be linked to and displayed in MainActivity.
    companion object {

        fun newInstance(): LearnersFragment {
            return LearnersFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment with its respective xml file.
        return inflater.inflate(R.layout.fragment_leaders, container, false)
    }

}
