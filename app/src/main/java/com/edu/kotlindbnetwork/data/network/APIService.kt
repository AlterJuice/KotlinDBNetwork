package com.edu.kotlindbnetwork.data.network

import com.edu.kotlindbnetwork.Consts
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET(Consts.API_URL_TAG)
    suspend fun getUsers(@Query(Consts.API_KEY_INCLUDE) includedParams: String,
                         @Query(Consts.API_KEY_COUNT_USERS) count: Int
                 ): UserResponse

    @GET(Consts.API_URL_TAG)
    suspend fun getUser(@Query(Consts.API_KEY_USER_ID) userId: String): UserResponse

    @GET(Consts.API_URL_TAG)
    suspend fun getUserById(@Query(Consts.API_KEY_USER_ID) userId: String): UserResponse

}