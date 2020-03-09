package com.sfjava.tunesfeed

import androidx.lifecycle.ViewModel

import androidx.lifecycle.ViewModelProvider
import com.sfjava.tunesfeed.data.FeedItemsRepository
import com.sfjava.tunesfeed.ui.home.FeedListViewModel

class FeedListViewModelFactory(itemsRepository: FeedItemsRepository) : ViewModelProvider.Factory {

    private val _itemsRepository: FeedItemsRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeedListViewModel(_itemsRepository) as T
    }

    init {
        _itemsRepository = itemsRepository
    }
}
