package com.edu.kotlindbnetwork.repo

import com.edu.kotlindbnetwork.APIService
import com.edu.kotlindbnetwork.Consts
import com.edu.kotlindbnetwork.db.user.User
import com.edu.kotlindbnetwork.response.toModel

class UserRepoNetwork(
    private val api: APIService
) : UserRepo {

    override suspend fun getUsers(): List<User> {
        return getUsers(Consts.countUsersPerRequest)
    }

    override suspend fun getUsers(count: Int): List<User> {
        return api.getUsers(Consts.includedParams, count).results.map {
            it.toModel()
        }
    }

    override suspend fun getUsers(count: Int, offset: Int): List<User> {
        return getUsers(count)
    }

    override suspend fun saveUsers(users: List<User>) {
        throw UnsupportedOperationException("Cannot save users to server")
    }

    override suspend fun getUserById(userId: String): User {
        throw java.lang.UnsupportedOperationException("Server doesn't allow to get users by id")
        // You have to use seed to get the same objects each time
        // api.getUserById(userId)
    }

    override suspend fun clearUsers() {
        throw UnsupportedOperationException("Cannot clear users from server")
    }

    override suspend fun clearUserById(userId: String) {
        throw UnsupportedOperationException("Cannot clear user from server")
    }

}