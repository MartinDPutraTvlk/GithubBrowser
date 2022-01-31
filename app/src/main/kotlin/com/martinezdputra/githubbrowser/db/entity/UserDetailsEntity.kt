package com.martinezdputra.githubbrowser.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDetailsEntity(
    @PrimaryKey val id: String,
    val dpUrl: String,
    val name: String? = null,
    val bio: String? = null,
    val company: String? = null,
    val location: String? = null,
    val email: String? = null,
    val followers: Int = 0,
    val following: Int = 0
)
