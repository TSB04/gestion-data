package com.example.data.database

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
    fun insertUser(user: User)  // suspend for background operation

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    fun getUserById(id: String): LiveData<User?>  // LiveData should not be suspend

    @Query("SELECT * FROM users")
    fun getAllUsersLiveData(): LiveData<List<User>>  // LiveData should not be suspend

    @Update
    fun updateUser(user: User)  // suspend for background operation

    @Delete
    fun deleteUser(user: User)  // suspend for background operation
}
