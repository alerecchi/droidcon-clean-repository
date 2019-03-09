package com.droidcon.cleanrepository.presentation.paginatedsource

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

class PaginatedSourceFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var baseViewModel: PaginatedViewModel
    private lateinit var adapter: PagedFeedAdapter

    companion object {
        fun newInstance() = PaginatedSourceFragment()
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
        baseViewModel = viewModel(viewModelFactory)
        baseViewModel.paginatedFeed.observe(this) {
            adapter.submitList(it)
        }
    }
}