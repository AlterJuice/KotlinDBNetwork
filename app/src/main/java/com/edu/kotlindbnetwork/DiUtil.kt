package com.edu.kotlindbnetwork

import android.content.Context
import androidx.room.Room
import com.edu.kotlindbnetwork.db.Database
import com.edu.kotlindbnetwork.repo.UserRepoDB
import com.edu.kotlindbnetwork.repo.UserRepoDecorator
import com.edu.kotlindbnetwork.repo.UserRepoNetwork
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DiUtil {
    private lateinit var contextProvider: () -> Context

    fun init(context: Context) {
        contextProvider = { context }
    }
    val databaseInstance by lazy {
        createDatabase()
    }

    val retrofitInstance by lazy {
        createRetrofitInstance()
    }

    val apiServiceInstance by lazy {
        createApiService()
    }

    val userRepoDecorator by lazy {
        UserRepoDecorator(UserRepoNetwork(apiServiceInstance), UserRepoDB(databaseInstance))
    }

    private fun createRetrofitInstance(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Consts.baseApiUrl).build()
    }

    private fun createDatabase(): Database{
        return Room.databaseBuilder(contextProvider(),
            Database::class.java, Consts.databaseFilename).build()
    }

    private fun createApiService(): APIService{
        return retrofitInstance.create(APIService::class.java)
    }

}