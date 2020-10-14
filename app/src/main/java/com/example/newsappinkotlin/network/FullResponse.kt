package com.example.newsappinkotlin.network


import com.example.newsappinkotlin.models.FullNewsModel
import com.google.gson.annotations.SerializedName

data class FullResponse(
    @SerializedName("articles") val articles: ArrayList<FullNewsModel>,
    @SerializedName("totalResults") val totalNews: Int,
    @SerializedName("status") val stat: String
)