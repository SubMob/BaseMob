/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
@file:Suppress("unused")

package com.github.mustafaozhan.basemob.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.github.mustafaozhan.basemob.lifecycle.SingleLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> LiveData<T>.reObserve(owner: LifecycleOwner, observer: Observer<T>) {
    removeObserver(observer)
    observe(owner, observer)
}

fun <T> SingleLiveData<T>.reObserveSingle(owner: LifecycleOwner, observer: Observer<T>) {
    removeObserver(observer)
    observe(owner, observer)
}

fun <T> Observable<T>.applySchedulers(): Observable<T> =
    observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
