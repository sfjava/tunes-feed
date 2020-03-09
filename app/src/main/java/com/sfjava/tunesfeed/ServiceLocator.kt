/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sfjava.tunesfeed

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.sfjava.tunesfeed.data.FeedItemsRepository
import com.sfjava.tunesfeed.data.MockFeedItemsRepository

/**
 * A Service Locator for the [FeedItemsRepository].
 *
 * NOTE: This is the MOCK version...
 */
object ServiceLocator {

    @Volatile
    var itemsRepository: FeedItemsRepository? = null
        @VisibleForTesting set

    fun provideTasksRepository(context: Context): FeedItemsRepository {
        synchronized(this) {
            return itemsRepository ?: itemsRepository ?: createTasksRepository(context)
        }
    }

    private fun createTasksRepository(context: Context): FeedItemsRepository {
        itemsRepository = MockFeedItemsRepository
        return itemsRepository as FeedItemsRepository
    }
}
