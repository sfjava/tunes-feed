package com.sfjava.tunesfeed

import android.app.Application
import com.sfjava.tunesfeed.data.model.FeedType
import com.sfjava.tunesfeed.data.source.FeedItemsRepository

class TunesFeedApplication : Application() {

    // NOTE: repo impl can depend here on the app flavor... (e.g. for mocking)
    fun feedItemsRepositoryForType(feedType: FeedType): FeedItemsRepository =
        ServiceLocator.provideFeedItemsRepository(this, feedType)

    // override fun onCreate() {
    //    super.onCreate()
    // }
}
