package com.sfjava.tunesfeed.data.source

import retrofit2.http.GET
import retrofit2.http.Path

interface RSSAppleMusic {
    @GET(value = "api/v1/us/apple-music/{feedTypePathSegment}/all/{count}/explicit.json")
    suspend fun fetchItemsFromFeed(
        @Path("feedTypePathSegment") feedTypePathSegment: String,
        @Path("count") count: Int
    ): FeedResponseDTO
}
