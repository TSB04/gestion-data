package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.dao.UserDao
import com.example.data.models.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDb : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDb? = null

        fun getDatabase(context: Context): UserDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDb::class.java,
                    "user_database"
                ).fallbackToDestructiveMigration()  // Ensure a fallback migration in case of schema changes
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
