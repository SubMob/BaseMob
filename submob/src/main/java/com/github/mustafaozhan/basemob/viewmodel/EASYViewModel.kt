package com.github.mustafaozhan.basemob.viewmodel

import androidx.lifecycle.LiveData
import com.github.mustafaozhan.basemob.model.BaseAction
import com.github.mustafaozhan.basemob.model.BaseEvent
import com.github.mustafaozhan.basemob.model.BaseState
import com.github.mustafaozhan.basemob.model.BaseYield

abstract class EASYViewModel
<State : BaseState, Action : BaseAction, Event : BaseEvent, Yield : BaseYield> :
    BaseViewModel() {
    abstract val states: State
    abstract val events: LiveData<Event>
    abstract fun getActions(): Action
    abstract val yields: Yield
}
