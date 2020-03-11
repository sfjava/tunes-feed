package com.sfjava.tunesfeed.ui.feedlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sfjava.tunesfeed.data.model.FeedType
import com.sfjava.tunesfeed.databinding.FeedListFragmentBinding
import com.sfjava.tunesfeed.ui.EventObserver
import com.sfjava.tunesfeed.ui.getViewModelFactory

class FeedListFragment : Fragment() {

    private val feedListViewModel
            by viewModels<FeedListViewModel> { getViewModelFactory(arguments?.get("feedType") as FeedType) }

    private lateinit var viewDataBinding: FeedListFragmentBinding
    private lateinit var listAdapter: FeedListAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FeedListFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = feedListViewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            listAdapter = FeedListAdapter(viewModel)
            viewDataBinding.itemsList.adapter = listAdapter
        } else {
            Log.w("TunesFeed", "ViewModel not initialized when attempting to set up adapter.")
        }

        viewModel?.showItemDetailEvent?.observe(viewLifecycleOwner, EventObserver {
            showItemDetail(it)
        })
    }

    private fun showItemDetail(id: String) {
        val action = FeedListFragmentDirections.actionFeedListFragmentToFeedItemDetailFragment(id)
        findNavController().navigate(action)
    }
}
