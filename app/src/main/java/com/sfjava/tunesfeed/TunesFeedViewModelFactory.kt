package com.sfjava.tunesfeed

import android.app.Application
import androidx.lifecycle.ViewModel

import androidx.lifecycle.ViewModelProvider
import com.sfjava.tunesfeed.ui.home.HomeViewModel

class TunesFeedViewModelFactory(param: String) : ViewModelProvider.Factory {

    private val mParam: String
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(mParam) as T // mApplication, mParam) as T
    }

    init {
        mParam = param
    }
}
