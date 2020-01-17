package com.mp.dailynews.data.network

import com.mp.dailynews.URL_PATH
import com.mp.dailynews.data.model.ArticlesResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApi {

    @GET(URL_PATH)
    fun getArticlesList(
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Flowable<ArticlesResponse>

}