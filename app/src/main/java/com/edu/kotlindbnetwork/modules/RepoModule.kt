package com.edu.kotlindbnetwork.modules;

import android.content.Context
import androidx.room.Room;
import com.edu.kotlindbnetwork.Consts

import com.edu.kotlindbnetwork.data.db.Database;
import com.edu.kotlindbnetwork.data.network.APIService
import com.edu.kotlindbnetwork.repo.UserRepo
import com.edu.kotlindbnetwork.repo.UserRepoDB
import com.edu.kotlindbnetwork.repo.UserRepoDecorator
import com.edu.kotlindbnetwork.repo.UserRepoNetwork

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RepoModule(
    private val context: Context
) {
    private fun getContext(): Context{
        return context
    }

    @Provides
    fun provideUserRepoDecorator(): UserRepo {
        return UserRepoDecorator(UserRepoNetwork(provideApiService()), UserRepoDB(provideDatabase()))
    }

    @Provides
    fun provideDatabase(): Database {
        return Room.databaseBuilder(getContext(),
                Database::class.java, Consts.DATABASE_FILENAME
        ).build()
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Consts.BASE_API_URL).build()
    }

    @Provides
    fun provideApiService(): APIService {
        return provideRetrofit().create(APIService::class.java)
    }


}
