package com.ekzakh.vknewsclient.data.network

import com.ekzakh.vknewsclient.data.model.NewsFeedResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("newsfeed.getRecommended?v=5.131")
    suspend fun fetchRecommendation(
        @Query("access_token") token: String,
    ): NewsFeedResponseDto
}
