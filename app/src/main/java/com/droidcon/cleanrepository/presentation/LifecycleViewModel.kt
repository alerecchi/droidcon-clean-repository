package com.droidcon.cleanrepository.presentation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModel

abstract class LifecycleViewModel : ViewModel(), LifecycleOwner {

    @Suppress("LeakingThis") //LifecycleRegistry holds a weak reference of the class
    private val lifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
    }

    override fun onCleared() {
        super.onCleared()
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
    }

    override fun getLifecycle() = lifecycleRegistry

}