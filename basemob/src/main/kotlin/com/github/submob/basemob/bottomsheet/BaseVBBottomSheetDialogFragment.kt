/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.basemob.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class BaseVBBottomSheetDialogFragment<TViewBinding : ViewBinding> :
    BaseBottomSheetDialogFragment() {

    private var _binding: TViewBinding? = null
    protected val binding: TViewBinding
        get() = _binding as TViewBinding

    abstract fun getViewBinding(): TViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = getViewBinding()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
