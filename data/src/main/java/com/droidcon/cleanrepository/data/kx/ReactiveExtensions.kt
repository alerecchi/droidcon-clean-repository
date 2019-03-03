package com.droidcon.cleanrepository.data.kx

import com.droidcon.cleanrepository.domain.SubscriptionContainer
import io.reactivex.disposables.Disposable

fun Disposable.bindToLifecycle(subscriptionContainer: SubscriptionContainer): Disposable {
    subscriptionContainer.bindToLifecycle(this)
    return this
}