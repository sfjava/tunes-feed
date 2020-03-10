package com.sfjava.tunesfeed.ui.feeds

import androidx.lifecycle.*
import com.sfjava.tunesfeed.data.model.FeedItem
import com.sfjava.tunesfeed.data.source.FeedItemsRepository
import com.sfjava.tunesfeed.data.source.Result
import kotlinx.coroutines.launch

class FeedListViewModel(val itemsRepository: FeedItemsRepository) : ViewModel() {

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
    val items: LiveData<List<FeedItem>> = _items

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    val empty: LiveData<Boolean> = Transformations.map(_items) {
        it.isEmpty()
    }

    val noItemsMessage: String = "No Items."

    init {
        loadItems(true)
    }

    fun loadItems(forceUpdate: Boolean) {
        _forceUpdate.value = forceUpdate
    }

    private fun filterItems(itemsResult: Result<List<FeedItem>>): LiveData<List<FeedItem>> {
        // TODO: this is a good case for liveData builder; replace when stable (per google's sample)
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