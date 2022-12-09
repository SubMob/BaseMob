/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.basemob.activity

import android.os.Bundle
import androidx.databinding.ViewDataBinding

abstract class BaseDBActivity<TDataBinding : ViewDataBinding> : BaseActivity() {

    private var _binding: TDataBinding? = null
    protected val binding: TDataBinding
        get() = _binding as TDataBinding

    abstract fun getDataBinding(): TDataBinding
    abstract fun onBinding(dataBinding: ViewDataBinding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getDataBinding()
        _binding?.lifecycleOwner = this
        onBinding(binding)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
