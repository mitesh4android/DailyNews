package com.mp.dailynews.presentation.utils

import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * WebClient is used to load url on WebView
 */
class WebClient() : WebViewClient() {
    /**
     * Overriding shouldOverrideUrlLoading to Load Url
     * @param view : WebView
     * @param url : String url
     * @return Boolean : loading status true or false
     */
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }
}