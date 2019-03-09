package com.droidcon.cleanrepository.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.droidcon.cleanrepository.R
import com.droidcon.cleanrepository.kx.viewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var feedViewModel: BaseFeedViewModel
    private lateinit var adapter: FeedAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FeedAdapter()
        activitiesList.adapter = adapter
        activitiesList.layoutManager = LinearLayoutManager(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        feedViewModel = viewModel(viewModelFactory)
        feedViewModel.feedList.observe(this) {
            adapter.submitList(it)
        }
    }

    abstract fun getViewModel(): BaseFeedViewModel

}
