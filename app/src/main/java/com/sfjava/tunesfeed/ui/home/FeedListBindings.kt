package com.sfjava.tunesfeed.ui.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<FeedItem>?) {
    items?.let {
        (listView.adapter as FeedListAdapter).submitList(items)
    }
}
