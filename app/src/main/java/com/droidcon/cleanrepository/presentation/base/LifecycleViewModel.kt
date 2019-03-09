package com.droidcon.cleanrepository.presentation.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModel
import com.droidcon.cleanrepository.domain.SubscriptionContainer

abstract class LifecycleViewModel : ViewModel(), LifecycleOwner {

    @Suppress("LeakingThis") //LifecycleRegistry holds a weak reference of the class
    private val lifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
    }

    override fun onCleared() {
        super.onCleared()
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
        subscriptionContainers.forEach { it.destroy() }
    }

    private val subscriptionContainers = mutableListOf<SubscriptionContainer>()

    protected fun addSubscriptionContainer(container: SubscriptionContainer) {
        subscriptionContainers.add(container)
    }

    override fun getLifecycle() = lifecycleRegistry

}