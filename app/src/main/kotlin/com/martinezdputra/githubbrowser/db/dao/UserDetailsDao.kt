package com.martinezdputra.githubbrowser.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.martinezdputra.githubbrowser.db.entity.UserDetailsEntity
import io.reactivex.Observable

@Dao
interface UserDetailsDao {
    @Query("SELECT * FROM UserDetailsEntity")
    fun getAll(): Observable<List<UserDetailsEntity>>

    @Query("SELECT * FROM UserDetailsEntity WHERE id = :id")
    fun getById(id: String): UserDetailsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateAll(vararg users: UserDetailsEntity)
}