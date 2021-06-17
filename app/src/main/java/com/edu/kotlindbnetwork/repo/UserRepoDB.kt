package com.edu.kotlindbnetwork.repo

import com.edu.kotlindbnetwork.db.Database
import com.edu.kotlindbnetwork.db.user.User

class UserRepoDB(
    private val database:Database
): UserRepoInterface {
    override suspend fun getUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun saveUsers(users: List<User>) {
        TODO("Not yet implemented")
    }
}