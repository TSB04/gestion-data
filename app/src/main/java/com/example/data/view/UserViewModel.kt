package com.example.data.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.data.database.Db
import com.example.data.models.User
import com.example.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository: UserRepository = UserRepository(Db.getDatabase(application).userDao())
    val allUsers: LiveData<List<User>> = userRepository.allUsers

    fun addUser(name: String, email: String) {
        viewModelScope.launch {
            userRepository.addUser(name, email)
        }
    }

    fun getUserById(id: String): LiveData<User?> {
        return userRepository.getUserById(id)
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            userRepository.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userRepository.deleteUser(user)
        }
    }
}
//class UserViewModel(application: Application) : AndroidViewModel(application) {
//
//    private val userDao: UserDao = AppDatabase.getDatabase(application).userDao()
//    val allUsers: LiveData<List<User>> = userDao.getAllUsers()
//
//    fun addUser(name: String, email: String) {
//        viewModelScope.launch {
//            userDao.insert(User(name = name, email = email))
//        }
//    }
//
//    fun deleteUser(user: User) {
//        viewModelScope.launch {
//            userDao.delete(user)
//        }
//    }
//}

