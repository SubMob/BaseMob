package com.github.mustafaozhan.basemob.viewmodel

import com.github.mustafaozhan.basemob.model.BaseData
import com.github.mustafaozhan.basemob.model.BaseEffect
import com.github.mustafaozhan.basemob.model.BaseEvent
import com.github.mustafaozhan.basemob.model.BaseState
import com.github.mustafaozhan.basemob.util.SingleLiveData

abstract class SEEDViewModel
<State : BaseState, Event : BaseEvent, Effect : BaseEffect, Data : BaseData> :
    BaseViewModel() {
    abstract val state: State
    abstract val effect: SingleLiveData<Effect>
    abstract val event: Event
    abstract val data: Data
}
