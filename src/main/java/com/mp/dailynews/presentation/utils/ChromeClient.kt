package com.mp.dailynews.presentation.utils

import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ProgressBar

/**
 * ChromeClient is used to show progress of page loading
 * @constructor progressBar : passing the ProgressBar to update the loading percentage
 */
class ChromeClient(private val progressBar: ProgressBar) : WebChromeClient() {
    /**
     * Updating the progress based on web page loaded percentage
     * @param view : WebView
     * @param progress : Percentage loaded
     */
    override fun onProgressChanged(view: WebView, progress: Int) {
        progressBar.progress = progress
        if (progress == 100) {
            progressBar.visibility = View.GONE
        } else {
            progressBar.visibility = View.VISIBLE
        }
    }
}