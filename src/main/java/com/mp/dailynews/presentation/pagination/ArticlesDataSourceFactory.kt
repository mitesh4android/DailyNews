package com.mp.dailynews.presentation.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mp.dailynews.data.model.Articles
import com.mp.dailynews.data.repository.ArticlesRepository
import io.reactivex.disposables.CompositeDisposable

/**
 * NewsDataSourceFactory is used to get the data and initializing the NewsDataSource to get the LiveData
 */
class ArticlesDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val repository: ArticlesRepository
) : DataSource.Factory<Int, Articles>() {

    val articlesDataSourceLiveData = MutableLiveData<ArticlesDataSource>()

    /**
     * Used to create the NewsDataSource and return Data
     */
    override fun create(): DataSource<Int, Articles> {
        val articlesDataSource = ArticlesDataSource(compositeDisposable, repository)
        articlesDataSourceLiveData.postValue(articlesDataSource)
        return articlesDataSource
    }
}