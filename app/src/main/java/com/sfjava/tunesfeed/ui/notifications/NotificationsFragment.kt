package com.sfjava.tunesfeed.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sfjava.tunesfeed.R
import com.sfjava.tunesfeed.TunesFeedViewModelFactory
import com.sfjava.tunesfeed.ui.home.HomeViewModel

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: HomeViewModel // NotificationsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this, TunesFeedViewModelFactory("P2")).get(HomeViewModel::class.java)
            // ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
