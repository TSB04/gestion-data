package com.example.data

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.ui.UserAdapter
import com.example.data.view.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize adapter and attach to RecyclerView
        userAdapter = UserAdapter(emptyList())
        recyclerView.adapter = userAdapter

        // Observe user list and update RecyclerView when data changes
        userViewModel.allUsers.observe(this, Observer { users ->
            users?.let {
                userAdapter.updateData(it)
            }
        })

        // Initialize the FloatingActionButton and set click listener
        val fabAddUser: FloatingActionButton = findViewById(R.id.fabAddUser)
        fabAddUser.setOnClickListener {
            showAddUserDialog()
        }
    }

    // Show dialog to add a new user
    private fun showAddUserDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_user, null)
        val nameEditText = dialogView.findViewById<EditText>(R.id.editTextUserName)
        val emailEditText = dialogView.findViewById<EditText>(R.id.editTextUserEmail)

        AlertDialog.Builder(this)
            .setTitle("Add User")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val name = nameEditText.text.toString().trim()
                val email = emailEditText.text.toString().trim()

                if (name.isNotEmpty() && email.isNotEmpty()) {
                    addNewUser(name, email)
                } else {
                    Toast.makeText(this, "Please enter both name and email", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }

    // Add a new user to the database
    private fun addNewUser(name: String, email: String) {
        userViewModel.addUser(name, email)
        Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show()
    }
}
