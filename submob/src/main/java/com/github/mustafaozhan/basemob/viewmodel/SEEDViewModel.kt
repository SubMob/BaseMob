package com.github.mustafaozhan.basemob.viewmodel

import androidx.lifecycle.MutableLiveData
import com.github.mustafaozhan.basemob.model.BaseData
import com.github.mustafaozhan.basemob.model.BaseEffect
import com.github.mustafaozhan.basemob.model.BaseEvent
import com.github.mustafaozhan.basemob.model.BaseState

abstract class SEEDViewModel
<State : BaseState, Event : BaseEvent, Effect : BaseEffect, Data : BaseData> :
    BaseViewModel() {
    abstract val state: State
    abstract val event: Event
    abstract val effect: MutableLiveData<Effect>
    abstract val data: Data
}
