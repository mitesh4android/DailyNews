package com.mp.dailynews.presentation.activities

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_new_detail.*
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.mp.dailynews.NEWS_URL
import com.mp.dailynews.R
import com.mp.dailynews.presentation.base.BaseActivity
import com.mp.dailynews.presentation.utils.ChromeClient
import com.mp.dailynews.presentation.utils.WebClient

/**
 * NewsDetailActivity is used to show news detail on web page (WebView)
 */
class ArticleWebActivity : BaseActivity() {

    private var URL: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_detail)
        setupToolBar()
        bindWebView()
    }

    /**
     * Setting up the toolbar to show back arrow and back functionality
     */
    private fun setupToolBar() {
        val toolBar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar)
        supportActionBar?.title = getString(R.string.app_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    /**
     * Checking the back click and returning to previous activity
     * @param item : MenuItem
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Setting up the WebView and getting the URL from intent and loading the url to WebView
     */
    fun bindWebView() {
        val receivedIntent = intent
        URL = receivedIntent.getStringExtra(NEWS_URL)
        // If URL is null return to previous screen
        if (URL == null) {
            finish()
        }
        // Setting WebClient to WebView
        webview.webViewClient = WebClient()
        // Setting WebChromeClient to WebView
        webview.webChromeClient = ChromeClient(progress_bar)
        webview.settings.loadsImagesAutomatically = true
        webview.settings.javaScriptEnabled = true
        webview.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        // Loading URL to WebView
        webview.loadUrl(URL)
    }
}