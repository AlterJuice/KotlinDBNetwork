package com.edu.kotlindbnetwork.repo

import com.edu.kotlindbnetwork.Consts
import com.edu.kotlindbnetwork.db.user.User
import java.lang.Exception

class UserRepoDecorator(
    private val networkRepo: UserRepo,
    private val databaseRepo: UserRepo
) : UserRepo {

    private var firstRequest = true


    private suspend fun clearDataIfFirstRequest(){
        if (firstRequest){
            firstRequest = false
            databaseRepo.clearUsers()
        }
    }

    override suspend fun getUsers(): List<User> {
        return getUsers(Consts.countUsersPerRequest)
    }

    override suspend fun getUsers(count: Int): List<User> {
        return getUsers(count, -1)
    }

    override suspend fun getUsers(count: Int, offset: Int): List<User> {
        return try {
            val users = getUsers(networkRepo, count, offset)
            clearDataIfFirstRequest()
            saveUsers(users)
            users
        } catch (e: Exception){
            e.printStackTrace()
            getUsers(databaseRepo, count, offset)
        }
    }

    private suspend fun getUsers(repo: UserRepo, count: Int, offset:Int): List<User> {
        if (offset == -1)
            if (count == -1)
                return repo.getUsers()
            else
                return repo.getUsers(count)
        return repo.getUsers(count, offset)
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

    override suspend fun clearUserById(userId: String) {
        try {
            networkRepo.clearUserById(userId)
        }catch (e: Exception){
        }
        databaseRepo.clearUserById(userId)
    }
}