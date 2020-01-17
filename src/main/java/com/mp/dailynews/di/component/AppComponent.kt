package com.mp.dailynews.di.component

import com.mp.dailynews.MyApplication
import com.mp.dailynews.di.module.ActivityBuilderModule
import com.mp.dailynews.di.module.AppModule
import com.mp.dailynews.di.module.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        AppModule::class,
        ViewModelFactoryModule::class
    ]
)

interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: MyApplication): AppComponent
    }
}
