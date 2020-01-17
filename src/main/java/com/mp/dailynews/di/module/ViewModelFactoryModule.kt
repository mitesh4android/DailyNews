package com.mp.dailynews.di.module

import androidx.lifecycle.ViewModelProvider
import com.mp.dailynews.presentation.base.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}