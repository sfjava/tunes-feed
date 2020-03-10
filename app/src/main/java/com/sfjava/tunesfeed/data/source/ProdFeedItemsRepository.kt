package com.sfjava.tunesfeed.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sfjava.tunesfeed.data.model.FeedItem
import com.sfjava.tunesfeed.data.model.FeedType
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class ProdFeedItemsRepository(val feedType: FeedType) : FeedItemsRepository {

    private val RSSBaseUrl = "https://rss.itunes.apple.com/"
    val api by lazy { retrofit(okhttp(), RSSBaseUrl).create(RSSAppleMusic::class.java) }

    private val observableItems = MutableLiveData<Result<List<FeedItem>>>()

    override fun observeItems(): LiveData<Result<List<FeedItem>>> = observableItems

    override suspend fun getItems(forceUpdate: Boolean): Result<List<FeedItem>> {
        return when(feedType) {
            FeedType.TopAlbums -> Result.Success(emptyList()) // mock "no items" for TopAlbums type
            else -> Result.Success(
                api.fetchItemsFromFeed("coming-soon", 10)
                    .feed
                    .results
                    .map { it.toFeedItem() }
            )
        }
    }

    override suspend fun refreshItems() = observableItems.postValue(getItems())

    @UseExperimental(UnstableDefault::class)
    private fun retrofit(callFactory: Call.Factory, baseUrl: String) = Retrofit.Builder()
        .callFactory(callFactory)
        .baseUrl(baseUrl)
        .addConverterFactory(Json(JsonConfiguration(strictMode = false))
            .asConverterFactory("application/json".toMediaType()))
        .build()

    private fun okhttp() = OkHttpClient.Builder().build()
}