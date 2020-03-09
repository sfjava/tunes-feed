package com.sfjava.tunesfeed.data

import androidx.lifecycle.LiveData
import com.sfjava.tunesfeed.ui.home.FeedItem

/**
 * Interface to the data layer.
 */
interface FeedItemsRepository {

    fun observeItems(): LiveData<Result<List<FeedItem>>>

    suspend fun getItems(forceUpdate: Boolean = false): Result<List<FeedItem>>

    suspend fun refreshItems()

//    fun observeItem(id: String): LiveData<Result<FeedItem>>
}