/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.basemob.view.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class BaseVBBottomSheetDialogFragment<TViewBinding : ViewBinding> : BaseBottomSheetDialogFragment() {

    protected lateinit var binding: TViewBinding

    abstract fun bind()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root
}
