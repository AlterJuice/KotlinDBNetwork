package com.edu.kotlindbnetwork.repo

import com.edu.kotlindbnetwork.data.db.user.User

class UserRepoDecorator(
    private val networkRepo: UserRepo,
    private val databaseRepo: UserRepo
) : UserRepo {

    private var firstRequest = true


    private suspend fun clearDataIfFirstRequest() {
        if (firstRequest) {
            firstRequest = false
            databaseRepo.clearUsers()
        }
    }


    override suspend fun getUsers(offset: Int, count: Int): List<User> {
        return try {
            val users = networkRepo.getUsers(offset, count)
            clearDataIfFirstRequest()
            saveUsers(users)
            users
        } catch (e: Exception) {
            databaseRepo.getUsers(offset, count)
        }
    }

    override suspend fun saveUsers(users: List<User>) {
        databaseRepo.saveUsers(users)
    }

    override suspend fun getUserById(userId: String): User {
        return try {
            networkRepo.getUserById(userId)
        } catch (e: Exception) {
            databaseRepo.getUserById(userId)
        }
    }

    override suspend fun clearUsers() {
        databaseRepo.clearUsers()
    }

}