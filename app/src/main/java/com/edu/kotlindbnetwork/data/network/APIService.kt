package com.edu.kotlindbnetwork.data.network

import com.edu.kotlindbnetwork.Consts
import com.edu.kotlindbnetwork.data.network.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET(Consts.API_URL_TAG)
    suspend fun getUsers(
        @Query(Consts.API_KEY_INCLUDE) includedParams: String,
        @Query(Consts.API_KEY_COUNT_USERS) count: Int,
        @Query(Consts.API_KEY_SEED_OFFSET) seedOffset: String
    ): UserResponse

}