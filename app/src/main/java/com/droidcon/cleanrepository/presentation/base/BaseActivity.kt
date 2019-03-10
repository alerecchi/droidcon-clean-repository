package com.droidcon.cleanrepository.presentation.base

import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

}