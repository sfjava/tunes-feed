package com.sfjava.tunesfeed.ui.feeds

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sfjava.tunesfeed.R
import com.sfjava.tunesfeed.data.model.FeedItem
import com.squareup.picasso.Picasso

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<FeedItem>?) {
    items?.let {
        (listView.adapter as FeedListAdapter).submitList(items)
    }
}

@BindingAdapter("app:imageUrl")
fun ImageView.setImageUrl(imageUrl: String) {
    if (imageUrl.isNotEmpty()) {
        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.image_loading)
            .error(R.drawable.image_unavailable)
            .into(this)
    }
}
