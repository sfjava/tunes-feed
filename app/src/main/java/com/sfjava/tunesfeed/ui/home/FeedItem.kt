package com.sfjava.tunesfeed.ui.home

import java.util.*

data class FeedItem(val name: String) {
    var id: String = UUID.randomUUID().toString()
}
