package com.edu.kotlindbnetwork

import android.content.Context
import androidx.room.Room
import com.edu.kotlindbnetwork.data.db.Database
import com.edu.kotlindbnetwork.data.network.APIService
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
    private val databaseInstance by lazy {
        createDatabase()
    }

    private val retrofitInstance by lazy {
        createRetrofitInstance()
    }

    private val apiServiceInstance by lazy {
        createApiService()
    }

    private val userRepoDecorator by lazy {
        UserRepoDecorator(UserRepoNetwork(apiServiceInstance), UserRepoDB(databaseInstance))
    }

    private fun createRetrofitInstance(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Consts.BASE_API_URL).build()
    }

    private fun createDatabase(): Database {
        return Room.databaseBuilder(contextProvider(),
            Database::class.java, Consts.DATABASE_FILENAME).build()
    }

    private fun createApiService(): APIService {
        return retrofitInstance.create(APIService::class.java)
    }

}