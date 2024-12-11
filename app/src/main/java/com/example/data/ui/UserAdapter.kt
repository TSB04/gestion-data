package com.example.data.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.R
import com.example.data.models.User

class UserAdapter(private var userList: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    // Inflate the item layout and create a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    // Bind the data (user) to the views in the RecyclerView item
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    // Return the size of the user list
    override fun getItemCount(): Int {
        return userList.size
    }

    // Method to update the data in the adapter
    fun updateData(newUsers: List<User>) {
        userList = newUsers
        notifyDataSetChanged()  // Notify the adapter that the data has changed
    }

    // ViewHolder class to hold references to the views in each item
   class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val userName = itemView.findViewById<TextView>(R.id.userName)
    private val userEmail = itemView.findViewById<TextView>(R.id.userEmail)

    fun bind(user: User) {
        userName.text = user.name
        userEmail.text = user.email
    }
}
}
