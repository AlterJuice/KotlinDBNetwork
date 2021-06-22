package com.edu.kotlindbnetwork.repo

import com.edu.kotlindbnetwork.Consts
import com.edu.kotlindbnetwork.data.db.user.User

interface UserRepo {

    suspend fun getUsers(): List<User> {
        return getUsers(0)
    }
    suspend fun getUsers(offset: Int): List<User> {
        return getUsers(offset, Consts.COUNT_USERS_PER_REQUEST)
    }
    suspend fun getUsers(offset: Int, count: Int): List<User>
    suspend fun saveUsers(users: List<User>)
    suspend fun getUserById(userId: String): User
    suspend fun clearUsers()

}