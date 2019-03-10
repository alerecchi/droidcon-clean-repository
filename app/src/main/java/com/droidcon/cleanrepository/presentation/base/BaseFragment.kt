package com.droidcon.cleanrepository.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.droidcon.cleanrepository.R
import com.droidcon.cleanrepository.domain.model.NetworkState
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var baseFeedViewModel: BaseFeedViewModel
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
        baseFeedViewModel = getViewModel()
        baseFeedViewModel.feedList.observe(this) {
            adapter.submitList(it)
        }
        baseFeedViewModel.networkState.observe(this) {
            when (it) {
                NetworkState.LOADING -> progressBar.visibility = View.VISIBLE
                NetworkState.COMPLETED -> progressBar.visibility = View.GONE
                NetworkState.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this.requireActivity(), "Network error. Showing cached results", Toast.LENGTH_LONG)
                        .show()
                }
                else -> {
                }
            }
        }
    }

    abstract fun getViewModel(): BaseFeedViewModel

}
