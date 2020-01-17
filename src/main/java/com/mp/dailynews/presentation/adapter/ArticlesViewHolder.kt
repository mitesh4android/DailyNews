package com.mp.dailynews.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.mp.dailynews.R
import com.mp.dailynews.data.model.Articles
import kotlinx.android.synthetic.main.item_news.view.*

/**
 * NewViewHolder to bind news into view
 * @param view: selected view
 */
class ArticlesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    /**
     * Bind news detail to view and loading image to ImageView
     * @param articles : Articles object
     * @param requestManager : used to load image on ImageView
     * @param clickListener : Item click listener object
     */
    fun bind(articles: Articles?, requestManager: RequestManager, clickListener: (Articles) -> Unit) {
        if (articles != null) {
            itemView.txt_news_name.text = articles.title
            requestManager.load(articles.urlToImage).into(itemView.img_news_banner)
            itemView.text_view_source.text = articles.source?.name
            itemView.setOnClickListener { clickListener(articles) }
        }
    }

    /**
     * Inflating view xml to ArticlesViewHolder and return ArticlesViewHolder
     * @return : ArticlesViewHolder
     */
    companion object {
        fun create(parent: ViewGroup): ArticlesViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
            return ArticlesViewHolder(view)
        }
    }
}