package com.example.data

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.databinding.ActivityMainBinding
import com.example.data.databinding.DialogAddUserBinding
import com.example.data.ui.UserAdapter
import com.example.data.view.UserViewModel

class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val userAdapter by lazy { UserAdapter(this::onUserClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate: Initializing MainActivity")

        setupRecyclerView()
        observeUsers()

        binding.fabAddUser.setOnClickListener {
            Log.d(TAG, "FloatingActionButton clicked to add a user")
            showAddUserDialog()
        }
    }

    private fun setupRecyclerView() {
        Log.d(TAG, "Setting up RecyclerView")
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }
    }

    private fun observeUsers() {
        userViewModel.allUsers.observe(this) { users ->
            Log.d(TAG, "Observed changes in user list: ${users.size} users")
            userAdapter.submitList(users)
        }
    }

    private fun showAddUserDialog() {
        Log.d(TAG, "Displaying Add User Dialog")
        val dialogBinding = DialogAddUserBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Add User")
            .setView(dialogBinding.root)
            .setPositiveButton("Add", null)
            .setNegativeButton("Cancel", null)
            .create()

        dialog.setOnShowListener {
            val addButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            addButton.setOnClickListener {
                val name = dialogBinding.editTextUserName.text.toString().trim()
                val email = dialogBinding.editTextUserEmail.text.toString().trim()

                if (name.isNotEmpty() && email.isNotEmpty()) {
                    Log.d(TAG, "Adding user: Name = $name, Email = $email")
                    userViewModel.addUser(name, email)
                    Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                } else {
                    Log.w(TAG, "Add User fields cannot be empty")
                    Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
        }

        dialog.show()
    }

    private fun onUserClicked(userId: Int) {
        Log.d(TAG, "User clicked: ID = $userId")
        UserDetailsActivity.start(this, userId)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
