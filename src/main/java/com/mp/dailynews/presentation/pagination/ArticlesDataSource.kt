package com.mp.dailynews.presentation.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.mp.dailynews.FIRST_PAGE
import com.mp.dailynews.PAGE_SIZE
import com.mp.dailynews.data.NetworkState
import com.mp.dailynews.data.model.Articles
import com.mp.dailynews.data.model.ArticlesResponse
import com.mp.dailynews.data.network.ResponseHandler
import com.mp.dailynews.data.repository.ArticlesRepository
import io.reactivex.disposables.CompositeDisposable

/**
 * ArticlesDataSource is used to load the data based on current page size and managing the load more data from repository api
 */
class ArticlesDataSource(
    private val compositeDisposable: CompositeDisposable,
    private val repository: ArticlesRepository
) : PageKeyedDataSource<Int, Articles>() {

    var networkState: MutableLiveData<NetworkState> = MutableLiveData()

    /**
     * When loading first time
     * @param params : we can get the params like number of page other detail
     * @param callback : is used to return the data and pass the current page and key to load more
     */
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Articles>
    ) {

        updateState(NetworkState.LOADING)

        repository.getArticles(
            PAGE_SIZE,
            FIRST_PAGE,
            object : ResponseHandler<ArticlesResponse> {
                override fun onRequestFailure(errorMessage: String?) {
                    updateState(NetworkState(NetworkState.Status.FAILED, errorMessage))
                }

                override fun onRequestSuccess(model: ArticlesResponse) {
                    callback.onResult(model.articles, null, FIRST_PAGE + 1)
                    updateState(NetworkState.LOADED)
                }
            }, compositeDisposable
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Articles>) {}

    /**
     * Load after is used to load more data when user is scrolled the page
     * @param params : Get the parameters like current page number and other detail
     * @param callback : is used to return the data and pass the current page and key to load more
     */
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Articles>) {
        val currentCount = params.key
        updateState(NetworkState.LOADING)
        repository.getArticles(
            PAGE_SIZE,
            currentCount,
            object : ResponseHandler<ArticlesResponse> {
                override fun onRequestFailure(errorMessage: String?) {
                    updateState(NetworkState(NetworkState.Status.FAILED, errorMessage))
                }

                override fun onRequestSuccess(model: ArticlesResponse) {
                    val currentCount = params.key
                    var key: Int? = null
                    if (loadMore(model.totalResults, currentCount)) {
                        key = currentCount + 1;
                    }
                    callback.onResult(model.articles, key)
                    updateState(NetworkState.LOADED)
                }
            }, compositeDisposable
        )
    }

    /**
     * Updating the network status on LiveData
     * @param state : NetworkState loaded, loading or failed
     */
    private fun updateState(state: NetworkState) {
        this.networkState.postValue(state)
    }

    /**
     * Check is last page is loaded or not
     * @param totalRecord : Total number of articles
     * @param currentPage : Current page load
     * @return is loading required or not
     */
    private fun loadMore(totalRecord: Int, currentPage: Int): Boolean {
        val totalPage = (totalRecord / PAGE_SIZE)
        return totalPage > currentPage
    }
}