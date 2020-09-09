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

//RecyclerView Adapter for Learners Fragment
class LearnersAdapter(context : Context, private val users : RequestResult) : RecyclerView.Adapter<LearnersAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.learning_leaders_item, parent, false)
        return ViewHolder(
            itemView = view
        )
    }

    override fun getItemCount(): Int = users.users.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(users.users[position])
    }

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val name : TextView = itemView.findViewById(R.id.name)
        private val hours : TextView = itemView.findViewById(R.id.hours)
        private val country : TextView = itemView.findViewById(R.id.country)

        fun bind(user : Users) {
            name.text = user.fullName
            hours.text = user.learningHours.toString()
            country.text = user.country
        }

    }

}
