package com.sfjava.tunesfeed.ui.home

import androidx.lifecycle.*
import com.sfjava.tunesfeed.data.FeedItemsRepository
import com.sfjava.tunesfeed.data.Result
import kotlinx.coroutines.launch

class FeedListViewModel(
    private val itemsRepository: FeedItemsRepository
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment $itemsRepository"
    }
    val text: LiveData<String> = _text

    private val _forceUpdate = MutableLiveData<Boolean>(false)

    private val _items: LiveData<List<FeedItem>> = _forceUpdate.switchMap { forceUpdate ->
        if (forceUpdate) {
            _dataLoading.value = true
            viewModelScope.launch {
                itemsRepository.refreshItems()
                _dataLoading.value = false
            }
        }
        itemsRepository.observeItems().distinctUntilChanged().switchMap { filterItems(it) }
    }

    val items: LiveData<List<FeedItem>> = _items // MutableLiveData<List<String>>() // FIXME

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    init {
        loadItems(true)
    }

    fun loadItems(forceUpdate: Boolean) {
        _forceUpdate.value = forceUpdate
    }

    private fun filterItems(itemsResult: Result<List<FeedItem>>): LiveData<List<FeedItem>> {
        // TODO: This is a good case for liveData builder. Replace when stable.
        val result = MutableLiveData<List<FeedItem>>()
        if (itemsResult is Result.Success) {
            // isDataLoadingError.value = false
            viewModelScope.launch {
                result.value = itemsResult.data
            }
        } else {
            result.value = emptyList()
            // showSnackbarMessage(R.string.loading_tasks_error)
            // isDataLoadingError.value = true
        }
        return result
    }
}