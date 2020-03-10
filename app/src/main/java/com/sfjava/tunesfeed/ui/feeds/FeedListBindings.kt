package com.sfjava.tunesfeed.ui.feeds

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sfjava.tunesfeed.data.model.FeedItem

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<FeedItem>?) {
    items?.let {
        (listView.adapter as FeedListAdapter).submitList(items)
    }
}
