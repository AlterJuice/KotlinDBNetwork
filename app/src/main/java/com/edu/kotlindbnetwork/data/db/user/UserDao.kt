package com.edu.kotlindbnetwork.data.db.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM users LIMIT :count OFFSET :offset")
    fun getUsers(count: Int, offset: Int): List<User>

    @Query("SELECT * FROM users WHERE uid LIKE :userId")
    fun getUserById(userId: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User>)

    @Query("DELETE FROM users")
    fun clearUsers()

}