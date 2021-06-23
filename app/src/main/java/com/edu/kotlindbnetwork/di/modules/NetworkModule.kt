package com.edu.kotlindbnetwork.di.modules

import com.edu.kotlindbnetwork.Consts
import com.edu.kotlindbnetwork.data.network.APIService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class NetworkModule {

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
