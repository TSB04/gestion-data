package com.example.data

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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

    private val userViewModel: UserViewModel by viewModels()
    private lateinit var binding: ActivityUserDetailsBinding
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate: Initializing UserDetailsActivity")

        val userId = intent.getIntExtra(EXTRA_USER_ID, -1)
        if (userId == -1) {
            Log.e(TAG, "Invalid user ID passed to UserDetailsActivity")
            showToast("Invalid user ID")
            finish()
            return
        }

        fetchUserDetails(userId)

        binding.buttonUpdateUser.setOnClickListener {
            Log.d(TAG, "Update User button clicked")
            updateUser()
        }

        binding.buttonDeleteUser.setOnClickListener {
            Log.d(TAG, "Delete User button clicked")
            deleteUser()
        }
    }

    private fun fetchUserDetails(userId: Int) {
        Log.d(TAG, "Fetching details for user ID: $userId")
        userViewModel.getUserById(userId.toString()).observe(this) { fetchedUser ->
            if (fetchedUser != null) {
                user = fetchedUser
                Log.d(TAG, "User fetched: $fetchedUser")
                displayUserDetails(fetchedUser)
            } else {
                Log.e(TAG, "User not found for ID: $userId")
                showToast("User not found")
                finish()
            }
        }
    }

    private fun displayUserDetails(user: User) {
        Log.d(TAG, "Displaying user details: $user")
        binding.editTextUserName.setText(user.name)
        binding.editTextUserEmail.setText(user.email)
    }

    private fun updateUser() {
        val updatedName = binding.editTextUserName.text.toString().trim()
        val updatedEmail = binding.editTextUserEmail.text.toString().trim()

        if (updatedName.isNotEmpty() && updatedEmail.isNotEmpty() && user != null) {
            val updatedUser = user!!.copy(name = updatedName, email = updatedEmail)
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    userViewModel.updateUser(updatedUser)
                }
                Log.d(TAG, "User updated: $updatedUser")
                showToast("User updated successfully")
                finish()
            }
        } else {
            Log.w(TAG, "Update User failed: Fields are empty or user is null")
            showToast("Please fill in all fields")
        }
    }

    private fun deleteUser() {
        user?.let {
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    userViewModel.deleteUser(it)
                }
                Log.d(TAG, "User deleted: $it")
                showToast("User deleted successfully")
                finish()
            }
        } ?: Log.e(TAG, "Delete User failed: User object is null")
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "UserDetailsActivity"
        private const val EXTRA_USER_ID = "userId"

        fun start(context: Context, userId: Int) {
            val intent = Intent(context, UserDetailsActivity::class.java).apply {
                putExtra(EXTRA_USER_ID, userId)
            }
            Log.d(TAG, "Starting UserDetailsActivity with userId = $userId")
            context.startActivity(intent)
        }
    }
}
