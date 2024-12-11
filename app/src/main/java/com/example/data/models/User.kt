package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "users")  // Correctly annotated with @Entity
data class User(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    val email: String
)
{
    init {
        require(name.isNotBlank()) { "Name must not be blank" }
        require(email.isNotBlank()) { "Email must not be blank" }
    }
}
