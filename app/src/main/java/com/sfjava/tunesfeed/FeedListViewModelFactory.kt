package com.sfjava.tunesfeed

import androidx.lifecycle.ViewModel

import androidx.lifecycle.ViewModelProvider
import com.sfjava.tunesfeed.ui.home.FeedListViewModel

class TunesFeedViewModelFactory(param: String) : ViewModelProvider.Factory {

    private val mParam: String
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeedListViewModel(mParam) as T // mApplication, mParam) as T
    }

    init {
        mParam = param
    }
}
