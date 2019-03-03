package com.droidcon.cleanrepository.domain

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class SubscriptionContainer {

    private val compositeDisposable = CompositeDisposable()

    fun bindToLifecycle(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun destroy() {
        compositeDisposable.clear()
    }
}