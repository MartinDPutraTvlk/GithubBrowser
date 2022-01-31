package com.martinezdputra.githubbrowser.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.martinezdputra.githubbrowser.db.dao.UserDetailsDao
import com.martinezdputra.githubbrowser.db.entity.UserDetailsEntity

@Database(entities = [UserDetailsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDetailDao(): UserDetailsDao

    companion object {
        const val DB_NAME = "github.browser.db"
    }
}