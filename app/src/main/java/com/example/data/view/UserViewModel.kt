package com.example.data.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.data.database.UserDb
import com.example.data.models.User
import com.example.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository: UserRepository = UserRepository(UserDb.getDatabase(application).userDao())
    val allUsers: LiveData<List<User>> = userRepository.allUsers

    // Add a new user (runs in background thread)
    fun addUser(name: String, email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(name, email)
        }
    }

    // Get a user by ID (returns LiveData)
    fun getUserById(id: String): LiveData<User?> {
        return userRepository.getUserById(id)
    }

    // Update an existing user (runs in background thread)
    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateUser(user)
        }
    }

    // Delete a user (runs in background thread)
    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteUser(user)
        }
    }
}


