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
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun provideUserRepo(
        @Named(Consts.MODULE_TAG_REPO_NETWORK) networkRepo: UserRepo,
        @Named(Consts.MODULE_TAG_REPO_DATABASE) databaseRepo: UserRepo
    ): UserRepo {
        return UserRepoDecorator(networkRepo, databaseRepo)
    }

    @Provides
    @Named(Consts.MODULE_TAG_REPO_NETWORK)
    fun provideRepoNetwork(apiService: APIService): UserRepo {
        return UserRepoNetwork(apiService)
    }

    @Provides
    @Named(Consts.MODULE_TAG_REPO_DATABASE)
    fun provideRepoDatabase(database: Database): UserRepo {
        return UserRepoDB(database)
    }

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java, Consts.DATABASE_FILENAME
        ).build()
    }
}
