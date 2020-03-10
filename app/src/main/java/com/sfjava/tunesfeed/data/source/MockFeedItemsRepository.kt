package com.sfjava.tunesfeed.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sfjava.tunesfeed.data.model.FeedItem
import com.sfjava.tunesfeed.data.model.FeedType

class MockFeedItemsRepository(val feedType: FeedType) : FeedItemsRepository {

    private var mockFeedItems =
        listOf(
            FeedItem("Item 1: $feedType"),
            FeedItem("Item 2: $feedType"),
            FeedItem("Item 3: $feedType"),
            FeedItem("Item 4: $feedType"),
            FeedItem("Item 5: $feedType"),
            FeedItem("Item 6: $feedType"),
            FeedItem("Item 7: $feedType"),
            FeedItem("Item 8: $feedType"),
            FeedItem("Item 9: $feedType"),
            FeedItem("Item 10: $feedType")
        )

    private val observableItems = MutableLiveData<Result<List<FeedItem>>>()

    override fun observeItems(): LiveData<Result<List<FeedItem>>> {
        return observableItems
    }

    override suspend fun getItems(forceUpdate: Boolean): Result<List<FeedItem>> {
        return when(feedType) {
            FeedType.TopAlbums -> Result.Success(emptyList()) // mock "no items" for TopAlbums type
            else -> Result.Success(mockFeedItems)
        }
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