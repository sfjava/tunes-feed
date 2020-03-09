package com.sfjava.tunesfeed.ui.home

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
import com.sfjava.tunesfeed.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var viewDataBinding: FragmentHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this, TunesFeedViewModelFactory("P1")).get(HomeViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
//        return root

        viewDataBinding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            viewmodel = homeViewModel
        }
        return viewDataBinding.root
    }
}
