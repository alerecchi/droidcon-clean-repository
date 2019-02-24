package com.droidcon.cleanrepository.presentation

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class DaggerFragment : Fragment(), HasSupportFragmentInjector {

    @JvmField
    @Inject
    var childFragmentInjector: DispatchingAndroidInjector<Fragment>? = null

    override fun onAttach(context: Context?) {
        if (shouldBeAndroidInjected())
            AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return childFragmentInjector
    }

    protected fun shouldBeAndroidInjected(): Boolean {
        return true
    }
}
