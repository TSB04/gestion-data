package com.example.data

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.data.view.UserViewModel
import com.example.data.models.User

class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Observe user data from ViewModel
        userViewModel.allUsers.observe(this, Observer { users ->
            // Update UI when the data changes
            users?.forEach { user ->
                Log.d("MainActivity", "User: ${user.name}, Email: ${user.email}")
            }
        })

        // Example of adding a new user
        userViewModel.addUser("Jane Doe", "jane.doe@example.com")
    }
}
