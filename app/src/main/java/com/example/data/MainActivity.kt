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

    // Using viewModels() to get the UserViewModel
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

        // Initialize the adapter
        userAdapter = UserAdapter(this)

        // Set RecyclerView layout and adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = userAdapter

        // Observe the list of users and submit the list to the adapter
        userViewModel.allUsers.observe(this, Observer { users ->
            userAdapter.submitList(users)
        })

        // FloatingActionButton click listener to show the add user dialog
        fabAddUser.setOnClickListener {
            showAddUserDialog()
        }
    }

    // Show the dialog to add a user
    private fun showAddUserDialog() {
        // Inflate the dialog layout
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_user, null)

        // Create and configure the AlertDialog
        val dialog = AlertDialog.Builder(this)
            .setTitle("Add User")
            .setView(dialogView)
            .setPositiveButton("Add", null)  // Initial positive button setup, will handle click in setOnShowListener
            .setNegativeButton("Cancel", null)
            .create()

        dialog.setOnShowListener {
            val button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            button.setOnClickListener {
                // Get the user input from the dialog
                val name = dialogView.findViewById<EditText>(R.id.editTextUserName).text.toString().trim()
                val email = dialogView.findViewById<EditText>(R.id.editTextUserEmail).text.toString().trim()

                // Validate the input fields
                if (name.isNotEmpty() && email.isNotEmpty()) {
                    // Add the user if both fields are filled
                    userViewModel.addUser(name, email)
                    Toast.makeText(this, "User added", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()  // Close the dialog
                } else {
                    // Show a Toast message if any field is empty
                    Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Show the dialog
        dialog.show()
    }
}
