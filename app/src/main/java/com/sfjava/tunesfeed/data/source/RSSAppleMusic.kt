package com.sfjava.tunesfeed.data.source

import com.sfjava.tunesfeed.data.model.FeedType
import retrofit2.http.GET
import retrofit2.http.Path

interface RSSAppleMusic {
    @GET(value = "api/v1/us/apple-music/{feedTypePathSegment}/all/{count}/explicit.json")
    suspend fun fetchItemsFromFeed(
        @Path("feedTypePathSegment") feedTypePathSegment: String,
        @Path("count") count: Int
    ): FeedResponseDTO
}

fun FeedType.toFeedTypePathSegment() =
    when(this) {
        FeedType.ComingSoon -> "coming-soon"
        FeedType.HotTracks -> "hot-tracks"
        FeedType.NewReleases -> "new-releases"
        FeedType.TopAlbums -> "top-albums"
    }
