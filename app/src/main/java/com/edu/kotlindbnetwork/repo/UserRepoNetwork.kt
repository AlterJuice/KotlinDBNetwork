package com.edu.kotlindbnetwork.repo

import com.edu.kotlindbnetwork.APIService
import com.edu.kotlindbnetwork.Consts
import com.edu.kotlindbnetwork.db.user.User
import com.edu.kotlindbnetwork.response.toModel

class UserRepoNetwork(
    private val api: APIService
) : UserRepoInterface {

    override suspend fun getUsers(): List<User> {

        return api.getUsers(Consts.countUsersPerRequest, Consts.includedParams).results.map {
            it.toModel()
        }
    }

    override suspend fun saveUsers(users: List<User>) {
        throw UnsupportedOperationException("Cannot save users to server")
    }

}