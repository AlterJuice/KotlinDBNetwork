package com.edu.kotlindbnetwork.repo

import com.edu.kotlindbnetwork.data.network.APIService
import com.edu.kotlindbnetwork.Consts
import com.edu.kotlindbnetwork.data.db.user.User
import com.edu.kotlindbnetwork.data.network.response.toModel

class UserRepoNetwork(
    private val api: APIService
) : UserRepo {
    private fun getOffsetForApi(offset: Int) : String{
        return "offset$offset"
    }

    override suspend fun getUsers(offset: Int, count: Int): List<User> {
        return api.getUsers(Consts.INCLUDED_PARAMS, count, getOffsetForApi(offset)).results.map {
            it.toModel()
        }
    }

    override suspend fun saveUsers(users: List<User>) {
        throw UnsupportedOperationException("Cannot save users to server")
    }

    override suspend fun getUserById(userId: String): User {
        throw java.lang.UnsupportedOperationException("Server doesn't allow to get users by id")
    }

    override suspend fun clearUsers() {
        throw UnsupportedOperationException("Cannot clear users from server")
    }

    override suspend fun clearUserById(userId: String) {
        throw UnsupportedOperationException("Cannot clear user from server")
    }

}