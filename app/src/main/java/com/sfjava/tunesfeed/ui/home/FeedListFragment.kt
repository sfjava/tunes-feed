package com.sfjava.tunesfeed.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sfjava.tunesfeed.databinding.FragmentFeedListBinding
import com.sfjava.tunesfeed.getViewModelFactory

class FeedListFragment : Fragment() {

    // private lateinit var feedListViewModel: FeedListViewModel
    private val feedListViewModel by viewModels<FeedListViewModel> { getViewModelFactory() }

    private lateinit var viewDataBinding: FragmentFeedListBinding
    private lateinit var listAdapter: FeedListAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
//        feedListViewModel =
//                ViewModelProviders.of(this, FeedListViewModelFactory("P1")).get(FeedListViewModel::class.java)

//        val root = inflater.inflate(R.layout.fragment_feed_list, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
//        return root

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
