package com.github.mustafaozhan.basemob.viewmodel

import androidx.lifecycle.LiveData
import com.github.mustafaozhan.basemob.model.BaseData
import com.github.mustafaozhan.basemob.model.BaseEffect
import com.github.mustafaozhan.basemob.model.BaseEvent
import com.github.mustafaozhan.basemob.model.BaseState

abstract class SEEDViewModel
<State : BaseState, Event : BaseEvent, Effect : BaseEffect, Data : BaseData> :
    BaseViewModel() {
    abstract val state: State
    abstract val effect: LiveData<Effect>
    abstract val event: Event
    abstract val data: Data
}
