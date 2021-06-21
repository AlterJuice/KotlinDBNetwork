package com.edu.kotlindbnetwork.data.db.user

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getUsers(): List<User>

    @Query("SELECT * FROM users LIMIT :count")
    fun getUsers(count: Int): List<User>

    @Query("SELECT * FROM users LIMIT :count OFFSET :offset")
    fun getUsers(count: Int, offset: Int): List<User>

    @Query("SELECT * FROM users WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM users WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Query("SELECT * FROM users WHERE uid LIKE :userId")
    fun getUserById(userId: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM users")
    fun clearUsers()

    @Query("DELETE FROM users WHERE uid like :userId")
    fun clearUserById(userId: String)
}