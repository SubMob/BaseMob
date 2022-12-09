/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.basemob.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

abstract class BaseDBBottomSheetDialogFragment<TDataBinding : ViewDataBinding> :
    BaseBottomSheetDialogFragment() {

    private var _binding: TDataBinding? = null
    protected val binding: TDataBinding
        get() = _binding as TDataBinding

    abstract fun getDataBinding(container: ViewGroup?): TDataBinding
    abstract fun onBinding(dataBinding: TDataBinding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = getDataBinding(container)
        _binding?.lifecycleOwner = viewLifecycleOwner
        onBinding(binding)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
