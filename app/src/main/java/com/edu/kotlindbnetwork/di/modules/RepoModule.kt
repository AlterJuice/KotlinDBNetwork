package com.edu.kotlindbnetwork.di.modules

import android.content.Context
import androidx.room.Room
import com.edu.kotlindbnetwork.Consts
import com.edu.kotlindbnetwork.data.db.Database
import com.edu.kotlindbnetwork.data.network.APIService
import com.edu.kotlindbnetwork.repo.UserRepo
import com.edu.kotlindbnetwork.repo.UserRepoDB
import com.edu.kotlindbnetwork.repo.UserRepoDecorator
import com.edu.kotlindbnetwork.repo.UserRepoNetwork


object RepoModule {

    fun provideUserRepo(
        networkRepo: UserRepo,
        databaseRepo: UserRepo
    ): UserRepo {
        return UserRepoDecorator(networkRepo, databaseRepo)
    }

    fun provideRepoNetwork(apiService: APIService): UserRepo {
        return UserRepoNetwork(apiService)
    }

    fun provideRepoDatabase(database: Database): UserRepo {
        return UserRepoDB(database)
    }

    fun provideDatabase(context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java, Consts.DATABASE_FILENAME
        ).build()
    }
}
