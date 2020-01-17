package com.mp.dailynews.data.model

import com.google.gson.annotations.SerializedName
import com.mp.dailynews.data.network.ArticlesModel

/**
 * NewsResponse data class
 */
data class ArticlesResponse(
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("totalResults")
    var totalResults: Int,
    @SerializedName("articles")
    var articles: List<Articles>
) : ArticlesModel