package com.iphint.testmagang.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.iphint.testmagang.retrofit.ApiServices
import com.iphint.testmagang.response.DataItem

class UserPagingSource(private val apiService: ApiServices) : PagingSource<Int, DataItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        val pageNumber = params.key ?: 1
        val pageSize = params.loadSize
        return try {
            val response = apiService.getAllUsers(pageNumber)
            if (response.isSuccessful) {
                val data = response.body()?.data?.filterNotNull() ?: emptyList()
                val prevKey = if (pageNumber == 1) null else pageNumber - 1
                val nextKey = if (data.isEmpty()) null else pageNumber + 1

                LoadResult.Page(data, prevKey, nextKey)
            } else {
                LoadResult.Error(Exception("Failed to load data, HTTP error: ${response.code()}"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}