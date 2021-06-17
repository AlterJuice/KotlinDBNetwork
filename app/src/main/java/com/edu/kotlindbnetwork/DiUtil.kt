package com.edu.kotlindbnetwork

import android.content.Context
import androidx.room.Room
import com.edu.kotlindbnetwork.db.Database
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