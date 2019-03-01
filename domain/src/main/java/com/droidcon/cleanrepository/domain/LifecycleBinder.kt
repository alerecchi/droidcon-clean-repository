package com.droidcon.cleanrepository.domain

import androidx.lifecycle.LifecycleOwner

interface LifecycleBinder : LifecycleOwner {

    fun bindTo(lifecycleOwner: LifecycleOwner)

}