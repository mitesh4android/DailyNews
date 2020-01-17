package com.mp.dailynews.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mp.dailynews.presentation.pagination.ArticlesDataSourceFactory
import com.mp.dailynews.PAGE_SIZE
import com.mp.dailynews.data.NetworkState
import com.mp.dailynews.data.model.Articles
import com.mp.dailynews.data.repository.ArticlesRepository
import com.mp.dailynews.presentation.base.BaseViewModel
import com.mp.dailynews.presentation.pagination.ArticlesDataSource
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * ArticlesViewModel is used to get the data from repository and pass the data in the form of LiveData to view
 */
class ArticlesViewModel @Inject constructor(private val repository: ArticlesRepository) :
    BaseViewModel() {

    private val articlesDataSourceFactory: ArticlesDataSourceFactory
    private val compositeDisposable = CompositeDisposable()

    var articlesList: LiveData<PagedList<Articles>>

    /**
     * Init creating the ArticlesDataSource object and setting up the ArticlesDataSourceFactory
     * Setting Pagination library to load Articles based on page size and other params
     */
    init {
        articlesDataSourceFactory = ArticlesDataSourceFactory(compositeDisposable, repository)
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
        articlesList = LivePagedListBuilder<Int, Articles>(articlesDataSourceFactory, config).build()
    }

    /**
     * getState is used to set the network status like loading fail or loaded
     */
    fun getState(): LiveData<NetworkState> =
        Transformations.switchMap<ArticlesDataSource, NetworkState>(
            articlesDataSourceFactory.articlesDataSourceLiveData,
            ArticlesDataSource::networkState
        )

    /**
     * Called when the ViewModel is being destroyed we are disposing all the RX subscription
     */
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}