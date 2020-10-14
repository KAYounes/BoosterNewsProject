package com.example.newsappinkotlin.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {

     val readAllData: LiveData<List<DataModel>>
    private val repository: Repository
    init {
        val newsDao = DataBase.getDatabase(application).newsDao()
        repository = Repository(newsDao)
        readAllData = repository.readAllData

    }
    fun saveNews(dataModel : DataModel){
    viewModelScope.launch(Dispatchers.IO) {
     repository.addNews(dataModel)
    }

    }

}