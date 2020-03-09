package com.sfjava.tunesfeed.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sfjava.tunesfeed.R
import com.sfjava.tunesfeed.getViewModelFactory
import com.sfjava.tunesfeed.ui.home.FeedListViewModel

class NotificationsFragment : Fragment() {

    private val feedListViewModel by viewModels<FeedListViewModel> { getViewModelFactory() }
    // private lateinit var notificationsViewModel: FeedListViewModel // NotificationsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
//        notificationsViewModel =
//            ViewModelProviders.of(this, FeedListViewModelFactory("P2")).get(FeedListViewModel::class.java)
//            // ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        feedListViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
