package com.droidcon.cleanrepository.presentation

abstract class BaseActivity : DaggerActivity() {

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        //setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

}