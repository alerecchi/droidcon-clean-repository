package com.droidcon.cleanrepository.domain

import androidx.lifecycle.*

class SimpleLifecycleBinder : LifecycleBinder, LifecycleObserver {

    var bindLifecycleOwner: LifecycleOwner? = null

    @Suppress("LeakingThis") //LifecycleRegistry holds a weak reference of the class
    private val lifecycleRegistry = LifecycleRegistry(this)

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
        bindLifecycleOwner?.lifecycle?.removeObserver(this)
        bindLifecycleOwner = null
    }

    override fun getLifecycle() = lifecycleRegistry

    override fun bindTo(lifecycleOwner: LifecycleOwner) {
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
        bindLifecycleOwner = lifecycleOwner
        bindLifecycleOwner?.lifecycle?.addObserver(this)
    }

}