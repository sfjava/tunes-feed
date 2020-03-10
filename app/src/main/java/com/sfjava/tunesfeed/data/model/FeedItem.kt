package com.sfjava.tunesfeed.data.model

import java.util.*

data class FeedItem(val name: String) {
    var id: String = UUID.randomUUID().toString()
}
