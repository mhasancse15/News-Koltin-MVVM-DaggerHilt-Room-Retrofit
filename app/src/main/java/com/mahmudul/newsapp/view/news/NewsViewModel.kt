package com.mahmudul.newsapp.view.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.mahmudul.newsapp.model.Article
import com.mahmudul.newsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository): ViewModel(){
    val list: LiveData<PagingData<Article>> = newsRepository.getAllNewsStream()

}

/*@ViewModelInject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val list: LiveData<PagingData<Article>> = newsRepository.getAllNewsStream()

}*/