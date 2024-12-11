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
    private lateinit var userAdapter: UserAdapter

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddUser: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views after setContentView
        recyclerView = findViewById(R.id.recyclerView)
        fabAddUser = findViewById(R.id.fabAddUser)

        // Set up RecyclerView
        userAdapter = UserAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = userAdapter

        // Observe changes in the users list
        userViewModel.allUsers.observe(this, Observer { users ->
            userAdapter.submitList(users)
        })

        // Set up the FloatingActionButton click listener
        fabAddUser.setOnClickListener {
            showAddUserDialog()
        }
    }

    private fun showAddUserDialog() {
        // Inflate dialog layout
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_user, null)

        // Show the dialog
        AlertDialog.Builder(this)
            .setTitle("Add User")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                // Get values from dialog input fields
                val name = dialogView.findViewById<EditText>(R.id.editTextUserName).text.toString().trim()
                val email = dialogView.findViewById<EditText>(R.id.editTextUserEmail).text.toString().trim()

                // Check if both fields are filled
                if (name.isNotEmpty() && email.isNotEmpty()) {
                    userViewModel.addUser(name, email)
                    Toast.makeText(this, "User added", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
