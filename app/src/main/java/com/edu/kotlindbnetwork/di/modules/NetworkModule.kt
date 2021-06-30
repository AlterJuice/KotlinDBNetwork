package com.edu.kotlindbnetwork.di.modules

import com.edu.kotlindbnetwork.Consts
import com.edu.kotlindbnetwork.data.network.APIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Consts.BASE_API_URL).build()
    }

    fun provideApiService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }
}
