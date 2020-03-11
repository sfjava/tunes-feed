package com.sfjava.tunesfeed.ui.feeditem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sfjava.tunesfeed.data.model.FeedType
import com.sfjava.tunesfeed.databinding.FeedItemDetailFragmentBinding
import com.sfjava.tunesfeed.ui.feeditem.FeedItemDetailFragmentArgs.Companion.fromBundle
import com.sfjava.tunesfeed.ui.getViewModelFactory

class FeedItemDetailFragment(): Fragment() {

    private val itemId by lazy {
        arguments?.let { fromBundle(it).itemId } ?: throw IllegalArgumentException("Expected arguments")
    }

    private val feedItemViewModel
            by viewModels<FeedItemViewModel> { getViewModelFactory(FeedType.ComingSoon) } // FIXME

    private lateinit var viewDataBinding: FeedItemDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FeedItemDetailFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = feedItemViewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}