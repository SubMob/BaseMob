package com.github.mustafaozhan.basemob.viewmodel

import androidx.lifecycle.MutableLiveData
import com.github.mustafaozhan.basemob.view.BaseViewEffect
import com.github.mustafaozhan.basemob.view.BaseViewEvent
import com.github.mustafaozhan.basemob.view.BaseViewState

abstract class UDFViewModel<
    ViewEffect : BaseViewEffect,
    ViewEvent : BaseViewEvent,
    ViewState : BaseViewState
    > : BaseViewModel() {
    abstract val viewEffectLiveData: MutableLiveData<ViewEffect>
    abstract val viewStateLiveData: MutableLiveData<ViewState>
    abstract fun getViewEvent(): ViewEvent
}
