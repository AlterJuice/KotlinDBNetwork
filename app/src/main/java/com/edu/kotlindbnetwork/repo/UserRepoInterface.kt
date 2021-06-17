package com.edu.kotlindbnetwork.repo

import com.edu.kotlindbnetwork.db.user.User

interface UserRepoInterface {

    suspend fun getUsers(): List<User>
    suspend fun saveUsers(users: List<User>)
}