package com.iphint.testmagang.retrofit

import com.iphint.testmagang.response.ResponseAllData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("users")
    suspend fun getAllUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 5
    ): Response<ResponseAllData>
}
