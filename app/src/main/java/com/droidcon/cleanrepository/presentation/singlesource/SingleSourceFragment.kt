package com.droidcon.cleanrepository.presentation.singlesource

import com.droidcon.cleanrepository.kx.viewModel
import com.droidcon.cleanrepository.presentation.base.BaseFragment

class SingleSourceFragment : BaseFragment() {

    companion object {
        fun newInstance() = SingleSourceFragment()
    }

    override fun getViewModel(): SingleFeedViewModel = viewModel(viewModelFactory)

}