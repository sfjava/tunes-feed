package com.sfjava.tunesfeed

import androidx.fragment.app.Fragment

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as TunesFeedApplication).itemsRepository
    return ViewModelFactory(repository, this)
}
