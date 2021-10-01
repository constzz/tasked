package com.gmail.konstantin.bezzemelnyi.common.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeOnce(owner: LifecycleOwner, observer: Observer<in T>) {
    this.observe(
        owner,
        { t ->
            observer.onChanged(t)
            removeObserver(observer)
        }
    )
}
