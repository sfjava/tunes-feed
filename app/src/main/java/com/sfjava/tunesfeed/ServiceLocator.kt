package com.sfjava.tunesfeed

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.sfjava.tunesfeed.data.model.FeedType
import com.sfjava.tunesfeed.data.source.FeedItemsRepository
import com.sfjava.tunesfeed.data.source.MockFeedItemsRepository
import com.sfjava.tunesfeed.data.source.ProdFeedItemsRepository

/**
 * A Service Locator providing the [FeedItemsRepository] for each [FeedType].
 */
object ServiceLocator {

    @Volatile
    var feedRepositoriesByType: MutableMap<FeedType, FeedItemsRepository> = mutableMapOf()
        @VisibleForTesting set

    fun provideFeedItemsRepository(context: Context, feedType: FeedType): FeedItemsRepository {
        synchronized(this) {
            return feedRepositoriesByType[feedType] ?: createFeedItemsRepository(context, feedType)
        }
    }

    private fun createFeedItemsRepository(context: Context, feedType: FeedType): FeedItemsRepository {

        // TODO: use the MOCKED impl depending on build-flavor and/or when running tests
        // return MockFeedItemsRepository(feedType).also {
        //    feedRepositoriesByType.put(feedType, it)
        // }

        return ProdFeedItemsRepository(feedType).also {
            feedRepositoriesByType.put(feedType, it)
        }
    }
}
