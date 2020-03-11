package com.sfjava.tunesfeed.ui.feeditem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sfjava.tunesfeed.data.model.FeedItem
import com.sfjava.tunesfeed.ui.feedlist.FeedListViewModel

class FeedItemViewModel(val feedListViewModel: FeedListViewModel): ViewModel() {

    private val _item = MutableLiveData<FeedItem>()
    val item: LiveData<FeedItem> = _item

    fun getItem(id: String) = feedListViewModel.getItem(id)
}