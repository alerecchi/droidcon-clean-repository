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
    private lateinit var adapter: PagedFeedAdapter
    private lateinit var pagedType: PagedType

    companion object {
        private const val TYPE_EXTRA = "TYPE_EXTRA"

        fun newInstance(pagedType: PagedType): PagedSourceFragment {
            val fragment = PagedSourceFragment()
            val extras = Bundle()
            extras.putInt(TYPE_EXTRA, pagedType.ordinal)
            fragment.arguments = extras
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        arguments?.let {
            pagedType = when (it.getInt(TYPE_EXTRA)) {
                PagedType.LOCAL.ordinal -> PagedType.LOCAL
                PagedType.REMOTE.ordinal -> PagedType.REMOTE
                else -> PagedType.LOCAL
            }
        }
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

        val pagedViewModel = when (pagedType) {
            PagedType.LOCAL -> {
                val pagedLocalViewModel: PagedLocalViewModel = viewModel(viewModelFactory)
                pagedLocalViewModel
            }
            PagedType.REMOTE -> {
                val pagedRemoteViewModel: PagedRemoteViewModel = viewModel(viewModelFactory)
                pagedRemoteViewModel
            }
        }

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
            }
        }
    }
}

enum class PagedType {
    LOCAL,
    REMOTE
}