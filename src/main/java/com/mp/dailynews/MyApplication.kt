package com.mp.dailynews

import com.mp.dailynews.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MyApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<MyApplication>? {
        return DaggerAppComponent.factory().create(this)
    }
}