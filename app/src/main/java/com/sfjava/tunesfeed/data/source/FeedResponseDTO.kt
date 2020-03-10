package com.sfjava.tunesfeed.data.source

import com.sfjava.tunesfeed.data.model.FeedItem
import kotlinx.serialization.Serializable

@Serializable
data class FeedResponseDTO(
    val feed: FeedDescriptionDTO
)

@Serializable
data class FeedDescriptionDTO(
    val results: Array<FeedResultDTO>
)

@Serializable
class FeedResultDTO(
    val name: String,
    val artistName: String = "",
    val artworkUrl100: String
)

//
// transform FeedResult DTO -> FeedItem Entity
//
fun FeedResultDTO.toFeedItem(): FeedItem {
    return FeedItem(name, artistName, artworkUrl100)
}