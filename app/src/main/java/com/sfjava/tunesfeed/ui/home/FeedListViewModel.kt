package com.sfjava.tunesfeed.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel(val param: String) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment $param"
    }
    val text: LiveData<String> = _text

    val items: LiveData<List<String>> = MutableLiveData<List<String>>() // FIXME
}