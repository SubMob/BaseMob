/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.basemob.activity

import android.os.Bundle
import androidx.viewbinding.ViewBinding

abstract class BaseVBActivity<TViewBinding : ViewBinding> : BaseActivity() {

    private var _binding: TViewBinding? = null
    protected val binding: TViewBinding
        get() = _binding as TViewBinding

    abstract fun getViewBinding(): TViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
