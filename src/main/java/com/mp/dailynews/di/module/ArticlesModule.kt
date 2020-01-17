package com.mp.dailynews.di.module

import com.mp.dailynews.data.network.ArticlesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ArticlesModule {

    @Provides
    fun provideNewsListApi(retrofit: Retrofit): ArticlesApi {
        return retrofit.create(ArticlesApi::class.java)
    }
}