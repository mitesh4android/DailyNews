package com.mp.dailynews.data.network

interface ResponseHandler<M : ArticlesModel> {
    fun onRequestFailure(errorMessage: String?)

    fun onRequestSuccess(model: M)
}