// Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
package com.github.mustafaozhan.basemob.viewmodel

import com.github.mustafaozhan.basemob.lifecycle.SingleLiveData
import com.github.mustafaozhan.basemob.model.BaseData
import com.github.mustafaozhan.basemob.model.BaseEffect
import com.github.mustafaozhan.basemob.model.BaseEvent
import com.github.mustafaozhan.basemob.model.BaseState

abstract class SEEDViewModel
<State : BaseState, Event : BaseEvent, Effect : BaseEffect, Data : BaseData> :
    BaseViewModel() {
    abstract val state: State
    abstract val effect: SingleLiveData<Effect>
    abstract val data: Data
    abstract fun getEvent(): Event
}
