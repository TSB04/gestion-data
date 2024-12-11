package com.example.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.models.User

@Dao
interface UserDao {

    @Insert
     fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    fun getUserById(id: String): LiveData<User?>  // Ensure this is nullable

//    @Query("SELECT * FROM users")
//    suspend fun getAllUsers(): List<User>

    // LiveData for observing user list
    @Query("SELECT * FROM users")
    fun getAllUsersLiveData(): LiveData<List<User>>

    @Update
     fun updateUser(user: User)

    @Delete
     fun deleteUser(user: User)
}
