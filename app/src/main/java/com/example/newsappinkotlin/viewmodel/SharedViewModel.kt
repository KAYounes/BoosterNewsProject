package com.example.newsappinkotlin.viewmodel


import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsappinkotlin.models.FullNewsModel
import com.example.newsappinkotlin.network.ApiCalls
import com.example.newsappinkotlin.network.ApiClient
import com.example.newsappinkotlin.network.FullResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class SharedViewModel: ViewModel() {

    private val headlinesMutLivData : MutableLiveData<ArrayList<FullNewsModel>> = MutableLiveData()
    private val headlinesMutLivDataStatus : MutableLiveData<String> = MutableLiveData()
    private val totalNews : MutableLiveData<Int> = MutableLiveData(1)
    private val headlineMutLivData : MutableLiveData<FullNewsModel> = MutableLiveData()


    fun getHeadlines(): LiveData<ArrayList<FullNewsModel>>{
        return headlinesMutLivData
    }

    fun getStatus(): LiveData<String>{
        return headlinesMutLivDataStatus
    }

    fun getHeadline(): LiveData<FullNewsModel>{
        return headlineMutLivData
    }

    fun getTotalSize(): LiveData<Int>{
        return totalNews
    }

    fun clickedHeadline(headline: FullNewsModel){
        headlineMutLivData.value = headline
    }


    fun getArticles(page: Int){
        println("actual page: #$page ${totalNews.value}")
        if(page <= totalNews.value!!/2 +1){
        ApiClient.topHeadlinesResponse(page, ::shareData, ::noData)
        }
    }

    private fun noData(reason: String) {
        headlinesMutLivDataStatus.value = reason
    }

    private fun shareData(headlines: ArrayList<FullNewsModel>, size: Int) {
        headlinesMutLivDataStatus.value = "success"
        headlinesMutLivData.value = headlines
        totalNews.value = size
    }

}
