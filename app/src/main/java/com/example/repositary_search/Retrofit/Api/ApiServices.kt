package com.example.repositary_search.Retrofit.Api

import com.example.repositary_search.Retrofit.models.Github_response
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("search/repositories")
    suspend fun getRepository(
        @Query("q") q: String = "Android",
        @Query("page") page: Int = 1,
    ): Response<Github_response>
}