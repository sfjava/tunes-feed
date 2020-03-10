package com.sfjava.tunesfeed.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sfjava.tunesfeed.data.model.FeedItem

object MockFeedItemsRepository : FeedItemsRepository {

    private var mockFeedItems =
        listOf(
            FeedItem("First Item"),
            FeedItem("Second Item"),
            FeedItem("Third Item")
        )

    private val observableItems = MutableLiveData<Result<List<FeedItem>>>()

    override fun observeItems(): LiveData<Result<List<FeedItem>>> {
        return observableItems
    }

    override suspend fun getItems(forceUpdate: Boolean): Result<List<FeedItem>> {
        return Result.Success(mockFeedItems)
    }

    override suspend fun refreshItems() {
        observableItems.postValue(
            getItems()
        )
    }

    // override fun observeItem(id: String): LiveData<Result<FeedItem>> {
    //    return observableItems.map { items ->
    //        when (items) {
    //            is Result.Loading -> Result.Loading
    //            is Error -> Error(items.exception)
    //            is Success -> {
    //                val task = items.data.firstOrNull() { it.id == id }
    //                    ?: return@map Error(Exception("Not found"))
    //                Success(task)
    //            }
    //        }
    //    }
    // }
}