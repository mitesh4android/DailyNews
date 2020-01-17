package com.mp.dailynews.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mp.dailynews.presentation.adapter.ArticlesAdapter
import com.bumptech.glide.RequestManager
import com.mp.dailynews.NEWS_URL
import com.mp.dailynews.R
import com.mp.dailynews.data.NetworkState
import com.mp.dailynews.data.model.Articles
import com.mp.dailynews.presentation.base.BaseActivity
import com.mp.dailynews.presentation.base.ViewModelProviderFactory
import com.mp.dailynews.presentation.viewmodel.ArticlesViewModel
import kotlinx.android.synthetic.main.activity_news_list.progress_bar
import kotlinx.android.synthetic.main.activity_news_list.recycler_view
import javax.inject.Inject

/**
 * NewsListActivity is used to display the news list
 */
class ArticlesActivity : BaseActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    @Inject
    lateinit var requestManager: RequestManager
    private lateinit var viewModel: ArticlesViewModel
    private lateinit var articlesAdapter: ArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        setupToolBar()
        viewModel = ViewModelProvider(this, providerFactory).get(ArticlesViewModel::class.java)
        initAdapter()
        initState()
    }

    /**
     * Setting up the toolbar
     */
    private fun setupToolBar() {
        val toolBar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar)
        supportActionBar?.title = getString(R.string.app_name)
    }

    /**
     * Setting up the Adapter and RecyclerView
     */
    private fun initAdapter() {
        articlesAdapter = ArticlesAdapter(requestManager) { articles: Articles -> itemClicked(articles) }
        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_view.adapter = articlesAdapter
        viewModel.articlesList.observe(this, Observer {
            articlesAdapter.submitList(it)
        })
    }

    /**
     * Checking the loading status and according the loading status showing ProgressBar
     */
    private fun initState() {
        viewModel.getState().observe(this, Observer { networkState ->
            if (networkState != null && networkState.status === NetworkState.Status.RUNNING) {
                progress_bar.visibility = View.VISIBLE
            } else {
                progress_bar.visibility = View.GONE
            }
        })
    }

    /**
     * RecyclerView item click listener and start a NewsDetailActivity
     * @param articles : News selected
     */
    private fun itemClicked(articles: Articles) {
        val intent = Intent(this@ArticlesActivity, ArticleWebActivity::class.java)
        intent.putExtra(NEWS_URL, articles.url)
        startActivity(intent)
    }
}
