package com.example.data.repository

import androidx.lifecycle.LiveData
import com.example.data.database.UserDao
import com.example.data.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<User>> = userDao.getAllUsersLiveData()

    suspend fun addUser(name: String, email: String) {
        val newUser = User(name = name, email = email)
        withContext(Dispatchers.IO) {
            userDao.insertUser(newUser)
        }
    }

    fun getUserById(id: String): LiveData<User?> {
        return userDao.getUserById(id)
    }

    suspend fun updateUser(user: User) {
        withContext(Dispatchers.IO) {
            userDao.updateUser(user)
        }
    }

    suspend fun deleteUser(user: User) {
        withContext(Dispatchers.IO) {
            userDao.deleteUser(user)
        }
    }
}
