package com.example.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.data.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)  // Avoid duplicate entries
     fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    fun getUserById(id: String): LiveData<User?>

    @Query("SELECT * FROM users")
    fun getAllUsersLiveData(): LiveData<List<User>>

    @Update
     fun updateUser(user: User)

    @Delete
     fun deleteUser(user: User)
}
