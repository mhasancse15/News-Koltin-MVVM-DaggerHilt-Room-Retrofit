package com.mahmudul.newsapp.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.mahmudul.newsapp.model.Article
import com.mahmudul.newsapp.paging.NewsPagingSource
import com.mahmudul.newsapp.service.NewsInterface

class NewsRepository(val newsInterface: NewsInterface) {

   fun getAllNewsStream(): LiveData<PagingData<Article>> = Pager(
       config = PagingConfig(
           20,
           5,
           enablePlaceholders = false
       ),
       pagingSourceFactory = {
           NewsPagingSource(newsInterface)
       }

   ).liveData
}