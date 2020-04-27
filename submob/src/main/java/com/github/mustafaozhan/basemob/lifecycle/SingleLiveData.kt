// Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
package com.github.mustafaozhan.basemob.lifecycle

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.github.mustafaozhan.basemob.error.SingleLiveDataException
import java.util.concurrent.atomic.AtomicBoolean

open class SingleLiveData<T> : LiveData<T>() {
    protected val pending = AtomicBoolean(false)

    @Throws
    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            throw SingleLiveDataException("Multiple observers registered.")
        }

        super.observe(owner, Observer {
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        })
    }
}
