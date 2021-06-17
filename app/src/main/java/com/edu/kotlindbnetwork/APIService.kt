package com.edu.kotlindbnetwork

import com.edu.kotlindbnetwork.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface APIService {

    @GET("api")
    suspend fun getUsers(@Query("results") resultsCount: Int,
                 @Query("inc") includedParams: String): UserResponse

}