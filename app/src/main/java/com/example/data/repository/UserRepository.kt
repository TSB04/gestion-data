package com.example.data.repository

import androidx.lifecycle.LiveData
import com.example.data.dao.UserDao
import com.example.data.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<User>> = userDao.getAllUsersLiveData()

    // Insert a user (runs on a background thread)
    suspend fun addUser(name: String, email: String) {
        val newUser = User(name = name, email = email)
        withContext(Dispatchers.IO) {
            userDao.insertUser(newUser)
        }
    }

    // Get a user by ID (runs on a background thread)
    suspend fun getUserById(id: String): User? {
        return withContext(Dispatchers.IO) {
            userDao.getUserById(id)
        }
    }

    // Update a user (runs on a background thread)
    suspend fun updateUser(user: User) {
        withContext(Dispatchers.IO) {
            userDao.updateUser(user)
        }
    }

    // Delete a user (runs on a background thread)
    suspend fun deleteUser(user: User) {
        withContext(Dispatchers.IO) {
            userDao.deleteUser(user)
        }
    }
}
