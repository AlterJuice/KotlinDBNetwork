package com.edu.kotlindbnetwork

import com.edu.kotlindbnetwork.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface APIService {

    @GET(Consts.apiUrlTag)
    suspend fun getUsers(@Query(Consts.apiKeyInclude) includedParams: String,
                         @Query(Consts.apiKeyCountUsers) count: Int
                 ): UserResponse

    @GET(Consts.apiUrlTag)
    suspend fun getUser(@Query(Consts.apiKeyUserId) userId: String): UserResponse

    @GET(Consts.apiUrlTag)
    suspend fun getUserById(@Query(Consts.apiKeyUserId) userId: String): UserResponse

}