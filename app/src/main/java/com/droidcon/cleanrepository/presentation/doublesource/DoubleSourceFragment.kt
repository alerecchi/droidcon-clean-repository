package com.droidcon.cleanrepository.presentation.doublesource

import com.droidcon.cleanrepository.kx.viewModel
import com.droidcon.cleanrepository.presentation.base.BaseFragment

class DoubleSourceFragment : BaseFragment() {

    companion object {
        fun newInstance() = DoubleSourceFragment()
    }

    override fun getViewModel(): DoubleViewModel = viewModel(viewModelFactory)
}