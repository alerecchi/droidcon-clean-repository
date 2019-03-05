package com.droidcon.cleanrepository.presentation.paginatedsource

import com.droidcon.cleanrepository.kx.viewModel
import com.droidcon.cleanrepository.presentation.base.BaseFragment
import com.droidcon.cleanrepository.presentation.doublesource.DoubleViewModel

class PaginatedSourceFragment : BaseFragment() {

    companion object {
        fun newInstance() = PaginatedSourceFragment()
    }

    override fun getViewModel(): DoubleViewModel = viewModel(viewModelFactory)
}