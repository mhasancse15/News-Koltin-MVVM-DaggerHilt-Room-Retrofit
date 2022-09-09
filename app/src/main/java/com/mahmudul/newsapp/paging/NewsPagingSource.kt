package com.mahmudul.newsapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.mahmudul.newsapp.model.Article
import com.mahmudul.newsapp.service.NewsInterface
import com.mahmudul.newsapp.utils.Constants
import java.io.IOException


const val STARTING_INDEX = 1
class NewsPagingSource (val newsInterface: NewsInterface): PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: STARTING_INDEX

        return try {
            val data = newsInterface.getAllNews(
                "in",
                "business",
                Constants.API_KEY,
                position,
                params.loadSize
            )
            LoadResult.Page(
                data = data.articles,
                prevKey = if (params.key == STARTING_INDEX) null else position - 1,
                nextKey = if (data.articles.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.let { anchorPage ->
                val pageIndex = state.pages.indexOf(anchorPage)
                if (pageIndex == 0) {
                    null
                } else {
                    state.pages[pageIndex - 1].nextKey
                }
            }
        }
    }
}