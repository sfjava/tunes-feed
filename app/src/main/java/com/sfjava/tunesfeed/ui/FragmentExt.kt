package com.sfjava.tunesfeed.ui

import androidx.fragment.app.Fragment
import com.sfjava.tunesfeed.TunesFeedApplication
import com.sfjava.tunesfeed.ViewModelFactory
import com.sfjava.tunesfeed.data.model.FeedType

fun Fragment.getViewModelFactory(feedType: FeedType): ViewModelFactory {
    val repository = (requireContext().applicationContext as TunesFeedApplication).feedItemsRepositoryForType(feedType)
    return ViewModelFactory(repository, this)
}
