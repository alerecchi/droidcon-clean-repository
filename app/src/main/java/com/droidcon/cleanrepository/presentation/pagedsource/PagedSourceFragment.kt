package com.droidcon.cleanrepository.presentation.pagedsource

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
import com.droidcon.cleanrepository.kx.viewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class PagedSourceFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var pagedViewModel: PagedViewModel
    private lateinit var adapter: PagedFeedAdapter

    companion object {
        fun newInstance() = PagedSourceFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PagedFeedAdapter()
        activitiesList.adapter = adapter
        activitiesList.layoutManager = LinearLayoutManager(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        pagedViewModel = viewModel(viewModelFactory)
        pagedViewModel.pagedFeed.observe(this) {
            adapter.submitList(it)
        }
        pagedViewModel.networkState.observe(this) {
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
}