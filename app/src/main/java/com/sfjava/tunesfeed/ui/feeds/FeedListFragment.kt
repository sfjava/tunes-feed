package com.sfjava.tunesfeed.ui.feeds

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sfjava.tunesfeed.data.model.FeedType
import com.sfjava.tunesfeed.databinding.FragmentFeedListBinding
import com.sfjava.tunesfeed.ui.getViewModelFactory

class FeedListFragment : Fragment() {

    private val feedListViewModel
            by viewModels<FeedListViewModel> { getViewModelFactory(arguments?.get("feedType") as FeedType) }

    private lateinit var viewDataBinding: FragmentFeedListBinding
    private lateinit var listAdapter: FeedListAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentFeedListBinding.inflate(inflater, container, false).apply {
            viewmodel = feedListViewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            listAdapter = FeedListAdapter(viewModel)
            viewDataBinding.itemsList.adapter = listAdapter
        } else {
            Log.w("TunesFeed", "ViewModel not initialized when attempting to set up adapter.")
        }
    }
}
