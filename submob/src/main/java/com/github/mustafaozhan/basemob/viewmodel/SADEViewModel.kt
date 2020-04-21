package com.github.mustafaozhan.basemob.viewmodel

import androidx.lifecycle.LiveData
import com.github.mustafaozhan.basemob.model.BaseAction
import com.github.mustafaozhan.basemob.model.BaseData
import com.github.mustafaozhan.basemob.model.BaseEffect
import com.github.mustafaozhan.basemob.model.BaseState

abstract class SADEViewModel
<State : BaseState, Action : BaseAction, Effect : BaseEffect, Data : BaseData> :
    BaseViewModel() {
    abstract val state: State
    abstract val effect: LiveData<Effect>
    abstract val event: Action
    abstract val data: Data
}
