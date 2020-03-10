package com.sfjava.tunesfeed.data.model

import java.util.*

data class FeedItem(val name: String, val artistName: String, val artworkUrl100: String) {
    var id: String = UUID.randomUUID().toString()

    val displayName
        get() = artistName.takeIf { it.isNotEmpty() } ?: name
}
