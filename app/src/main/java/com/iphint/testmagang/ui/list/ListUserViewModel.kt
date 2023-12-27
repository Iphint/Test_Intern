package com.iphint.testmagang.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.iphint.testmagang.paging.UserPagingSource
import com.iphint.testmagang.retrofit.ApiConfig

class ListUserViewModel : ViewModel() {
    val users = Pager(
        PagingConfig(pageSize = 5, enablePlaceholders = false),
        pagingSourceFactory = { UserPagingSource(ApiConfig.getApiServices()) }
    ).flow.cachedIn(viewModelScope)
}