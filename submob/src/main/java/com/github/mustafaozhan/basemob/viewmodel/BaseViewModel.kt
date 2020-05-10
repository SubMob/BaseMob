/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.basemob.viewmodel

import androidx.lifecycle.ViewModel
import com.github.mustafaozhan.basemob.extension.applySchedulers
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable by lazy { CompositeDisposable() }

    fun <T> subscribeService(
        serviceObservable: Flowable<T>,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) = compositeDisposable.add(
        serviceObservable.applySchedulers().subscribe(onSuccess, onError)
    )

    override fun onCleared() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
        super.onCleared()
    }
}
