package com.mp.dailynews.di.module

import androidx.lifecycle.ViewModel
import com.mp.dailynews.di.ViewModelKey
import com.mp.dailynews.presentation.viewmodel.ArticlesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ArticlesViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ArticlesViewModel::class)
    abstract fun bindNewsListViewModel(articlesViewModel: ArticlesViewModel): ViewModel
}