package com.example.data

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.data.databinding.ActivityUserDetailsBinding
import com.example.data.models.User
import com.example.data.view.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        Toast.makeText(this, "Hello, you are in UserDetails view", Toast.LENGTH_SHORT).show()
    }

//    private val userViewModel: UserViewModel by viewModels()
//    private lateinit var binding: ActivityUserDetailsBinding
//
//    private var user: User? = null // Nullable user object to hold the user data
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        // Using ViewBinding to access views
//        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Get the user ID from the Intent
//        val userId = intent.getStringExtra("userId") ?: run {
//            showToast("Invalid user ID")
//            finish()
//            return
//        }
//
//        // Fetch user details using the user ID
//        userViewModel.getUserById(userId).observe(this) { fetchedUser ->
//            if (fetchedUser != null) {
//                user = fetchedUser
//                displayUserDetails(fetchedUser)
//            } else {
//                showToast("User not found")
//                finish()  // Close activity if user not found
//            }
//        }
//
//
//        // Handle the update user button click
//        binding.buttonUpdateUser.setOnClickListener {
//            val updatedName = binding.editTextUserName.text.toString().trim()
//            val updatedEmail = binding.editTextUserEmail.text.toString().trim()
//
//            if (updatedName.isNotEmpty() && updatedEmail.isNotEmpty() && user != null) {
//                val updatedUser = user?.copy(name = updatedName, email = updatedEmail)
//                updatedUser?.let {
//                    lifecycleScope.launch {
//                        withContext(Dispatchers.IO) {
//                            userViewModel.updateUser(it)
//                        }
//                    }
//                    showToast("User updated successfully")
//                    finish() // Close activity after update
//                }
//            } else {
//                showToast("Please fill in all fields")
//            }
//        }
//
//
//        // Handle the delete user button click
//        binding.buttonDeleteUser.setOnClickListener {
//            user?.let {
//                userViewModel.deleteUser(it)
//                showToast("User deleted successfully")
//                finish() // Close activity after delete
//            } ?: showToast("Unable to delete user!")
//        }
//    }
//
//    // Function to display the user details in the EditText fields
//    private fun displayUserDetails(user: User) {
//        binding.editTextUserName.setText(user.name)
//        binding.editTextUserEmail.setText(user.email)
//    }
//
//    // Helper function to show toast messages
//    private fun showToast(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//    }
}
