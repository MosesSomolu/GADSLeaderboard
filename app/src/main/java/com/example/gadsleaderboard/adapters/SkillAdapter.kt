package com.example.gadsleaderboard.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gadsleaderboard.R
import com.example.gadsleaderboard.models.RequestResult
import com.example.gadsleaderboard.models.Users

//RecyclerView Adapter for Skill Fragment
class SkillAdapter(context : Context, private val users : RequestResult) : RecyclerView.Adapter<SkillAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillAdapter.ViewHolder {
        val view = layoutInflater.inflate(R.layout.skill_iq_leaders_item, parent, false)
        return ViewHolder(itemView = view)
    }

    override fun getItemCount(): Int = users.users.size

    override fun onBindViewHolder(holder: SkillAdapter.ViewHolder, position: Int) {
        holder.bind(users.users[position])
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val name : TextView = itemView.findViewById(R.id.name)
        private val skill : TextView = itemView.findViewById(R.id.skill)
        private val country : TextView = itemView.findViewById(R.id.country)

        fun bind(user : Users){
            name.text = user.fullName
            skill.text = user.skillIq.toString()
            country.text = user.country

        }

    }
}