package com.mp.dailynews.di.module

import com.mp.dailynews.presentation.activities.ArticleWebActivity
import com.mp.dailynews.presentation.activities.ArticlesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [ArticlesViewModelModule::class, ArticlesModule::class])
    abstract fun contributeNewsListActivity(): ArticlesActivity

    @ContributesAndroidInjector()
    abstract fun contributeNewsDetailActivity(): ArticleWebActivity
}