package com.sfjava.tunesfeed.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sfjava.tunesfeed.data.model.FeedItem
import com.sfjava.tunesfeed.data.model.FeedType

class MockFeedItemsRepository(val feedType: FeedType) : FeedItemsRepository {

    private var mockFeedItems =
        listOf(
            FeedItem("Item 1: $feedType", "artistName1", "http://foo.com"),
            FeedItem("Item 2: $feedType", "artistName2", "http://foo.com"),
            FeedItem("Item 3: $feedType", "artistName3", "http://foo.com"),
            FeedItem("Item 4: $feedType", "artistName4", "http://foo.com"),
            FeedItem("Item 5: $feedType", "artistName5", "http://foo.com"),
            FeedItem("Item 6: $feedType", "artistName6", "http://foo.com"),
            FeedItem("Item 7: $feedType", "artistName7", "http://foo.com"),
            FeedItem("Item 8: $feedType", "artistName8", "http://foo.com"),
            FeedItem("Item 9: $feedType", "artistName9", "http://foo.com"),
            FeedItem("Item 10: $feedType", "artistName10", "http://foo.com")
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