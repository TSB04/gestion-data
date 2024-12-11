package com.example.data

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.data.models.User
import com.example.data.view.UserViewModel

class UserDetailsActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()
    private lateinit var editTextUserName: EditText
    private lateinit var editTextUserEmail: EditText
    private lateinit var buttonUpdateUser: Button
    private lateinit var buttonDeleteUser: Button

    private var user: User? = null // Initialize as nullable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        // Initialize views
        editTextUserName = findViewById(R.id.editTextUserName)
        editTextUserEmail = findViewById(R.id.editTextUserEmail)
        buttonUpdateUser = findViewById(R.id.buttonUpdateUser)
        buttonDeleteUser = findViewById(R.id.buttonDeleteUser)

        // Get user data passed via Intent
        val userId = intent.getStringExtra("userId") // userId should be non-nullable here

        if (!userId.isNullOrEmpty()) {
            // Observe the user only once
            userViewModel.getUserById(userId).observe(this) { fetchedUser ->
                if (fetchedUser != null) {
                    user = fetchedUser
                    displayUserDetails(fetchedUser)
                } else {
                    Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                    finish()  // Close if user doesn't exist
                }
            }
        } else {
            Toast.makeText(this, "Invalid user ID", Toast.LENGTH_SHORT).show()
            finish()
        }

        // Handle Update Button Click
        buttonUpdateUser.setOnClickListener {
            val updatedName = editTextUserName.text.toString().trim()
            val updatedEmail = editTextUserEmail.text.toString().trim()

            if (updatedName.isNotEmpty() && updatedEmail.isNotEmpty() && user != null) {
                user?.let {
                    // Use copy function to create a new User object with updated values
                    val updatedUser = it.copy(name = updatedName, email = updatedEmail)
                    userViewModel.updateUser(updatedUser)
                    Toast.makeText(this, "User updated successfully", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle Delete Button Click
        buttonDeleteUser.setOnClickListener {
            user?.let {
                userViewModel.deleteUser(it)
                Toast.makeText(this, "User deleted successfully", Toast.LENGTH_SHORT).show()
                finish() // Close the activity
            } ?: Toast.makeText(this, "Unable to delete user!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayUserDetails(user: User?) {
        editTextUserName.setText(user?.name)
        editTextUserEmail.setText(user?.email)
    }
}
