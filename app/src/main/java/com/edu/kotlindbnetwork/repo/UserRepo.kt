package com.edu.kotlindbnetwork.repo

import com.edu.kotlindbnetwork.db.data.user.User

interface UserRepo {

    suspend fun getUsers(): List<User>
    suspend fun getUsers(count: Int): List<User>
    suspend fun getUsers(count: Int, offset: Int): List<User>
    suspend fun saveUsers(users: List<User>)
    suspend fun getUserById(userId: String): User
    suspend fun clearUsers()
    suspend fun clearUserById(userId: String)

}