package com.sfjava.tunesfeed.ui.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("items")
fun setItems(listView: RecyclerView, items: List<String>?) {
//    items?.let {
//        (listView.adapter as TunesFeedListAdapter).setData(items)
//    }
}
