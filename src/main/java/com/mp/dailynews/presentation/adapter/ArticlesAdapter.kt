package com.mp.dailynews.presentation.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import com.mp.dailynews.data.model.Articles

/**
 * News RecyclerView Adapter to show list
 * @param requestManager: RequestManger glide to load images of news
 * @param clickListener : Item click listener
 */
class ArticlesAdapter(var requestManager: RequestManager, val clickListener: (Articles) -> Unit) :
    PagedListAdapter<Articles, ArticlesViewHolder>(
        NewsDiffCallback
    ) {

    /**
     * OnCreateViewHolder to create view holder of adapter
     * @param parent : ViewGroup
     * @param viewType : ViewType if used multiple view type
     * @return : return the created ViewHolder (NewsViewHolder)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        return ArticlesViewHolder.create(parent)
    }

    /**
     * OnBindView is to bind the view and set the data to ViewHolder
     * @param holder : NewsViewHolder
     * @param position : ViewHolder position
     */
    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.bind(getItem(position), requestManager, clickListener)
    }

    /***
     * DiffUtil to find difference between old list items and new items
     */
    companion object {
        val NewsDiffCallback = object : DiffUtil.ItemCallback<Articles>() {
            override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
                return oldItem == newItem
            }
        }
    }

}