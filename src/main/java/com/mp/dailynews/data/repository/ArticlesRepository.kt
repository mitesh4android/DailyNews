package com.mp.dailynews.data.repository

import com.mp.dailynews.API_KEY
import com.mp.dailynews.COUNTRY
import com.mp.dailynews.data.model.ArticlesResponse
import com.mp.dailynews.data.network.ArticlesApi
import com.mp.dailynews.data.network.ResponseHandler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * NewsRepository is used to load the news from api call
 */
class ArticlesRepository @Inject constructor() {

    @Inject
    lateinit var articlesApi: ArticlesApi

    /**
     * Loading the news from API call
     * @param pageSize : number of item to load
     * @param currentPage : current number of page to load
     * @param responseHandler : callback object to return data
     * @param compositeDisposable : to add the subscription dispose object and destroy the subscription when ViewModel is destroy
     */
    fun getArticles(
        pageSize: Int,
        currentPage: Int,
        responseHandler: ResponseHandler<ArticlesResponse>,
        compositeDisposable: CompositeDisposable
    ) {

        articlesApi.getArticlesList(
            pageSize,
            currentPage,
            COUNTRY,
            API_KEY
        ).toObservable().subscribeOn(Schedulers.io())
            .subscribe(object : io.reactivex.Observer<ArticlesResponse> {
                override fun onSubscribe(disposable: Disposable) {
                    compositeDisposable.add(disposable)
                }

                override fun onNext(articlesResponse: ArticlesResponse) {
                    responseHandler.onRequestSuccess(articlesResponse)
                }

                override fun onError(e: Throwable) {
                    responseHandler.onRequestFailure(e.message)
                }

                override fun onComplete() {
                }
            })
    }
}