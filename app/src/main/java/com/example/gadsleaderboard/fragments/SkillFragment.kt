package com.example.gadsleaderboard.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gadsleaderboard.R

// Fragment linked to MainActivity and Viewpager to display data on the top Skill IQ learners
class SkillFragment : Fragment() {

    //This is created to enable the Fragment Manager called in MainActivity to create the newInstance
    //of SkillFragment so that it can be linked to and displayed in MainActivity.
    companion object {

        fun newInstance(): SkillFragment {
            return SkillFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment with its respective xml file.
        return inflater.inflate(R.layout.fragment_skill, container, false)
    }

    internal inner class SkillAdapter(context : Context) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItemCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    internal inner class ViewHolder(){

    }

}
