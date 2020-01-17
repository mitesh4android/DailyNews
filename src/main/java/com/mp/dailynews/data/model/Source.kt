package com.mp.dailynews.data.model

import com.google.gson.annotations.SerializedName
import com.mp.dailynews.data.network.ArticlesModel

/**
 * Source data class
 */
data class Source(
    @SerializedName("id")
    var id: Any? = null,
    @SerializedName("name")
    var name: String? = null
): ArticlesModel