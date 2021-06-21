package com.edu.kotlindbnetwork.repo

import com.edu.kotlindbnetwork.db.Database
import com.edu.kotlindbnetwork.db.data.user.User

class UserRepoDB(
    private val database:Database
): UserRepo {
    override suspend fun getUsers(): List<User> {
        return database.userDao()?.getUsers()!!
    }

    override suspend fun getUsers(count: Int): List<User> {
        return database.userDao()?.getUsers(count)!!
    }

    override suspend fun getUsers(count: Int, offset: Int): List<User> {
        return database.userDao()?.getUsers(count, offset)!!
    }

    override suspend fun saveUsers(users: List<User>) {
        database.userDao()?.insertAll(users)
    }

    override suspend fun getUserById(userId: String): User {
        return database.userDao()?.getUserById(userId)!!
    }

    override suspend fun clearUsers() {
        database.userDao()?.clearUsers()
    }

    override suspend fun clearUserById(userId: String) {
        database.userDao()?.clearUserById(userId)
    }
}