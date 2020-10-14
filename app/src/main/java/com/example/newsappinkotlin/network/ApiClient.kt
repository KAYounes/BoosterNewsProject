package com.example.newsappinkotlin.network

import android.widget.Toast
import com.example.newsappinkotlin.models.FullNewsModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val baseURL = "https://newsapi.org/"
    val service: ApiCalls
    private var retrofit: Retrofit? = null

    init {
        val retrofit =
            Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(ApiCalls::class.java)
    }

    fun callGetNews(
        currentPage: Int,
        noError: (headliens: ArrayList<FullNewsModel>) -> Unit,
        error: () -> Unit
    ) {
        service?.getNews(currentPage = currentPage)!!
            .enqueue(object : retrofit2.Callback<FullResponse?> {
                override fun onFailure(call: Call<FullResponse?>, t: Throwable) {
                    error.invoke()
                }

                override fun onResponse(
                    call: Call<FullResponse?>,
                    response: Response<FullResponse?>
                ) {
                    if (response.body() == null) {
                        error.invoke()
                    } else {
                        noError.invoke(response.body()!!.articles)
                    }
                }

            })
    }

}