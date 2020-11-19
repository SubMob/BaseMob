/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.basemob.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

abstract class BaseDBDialogFragment<TDataBinding : ViewDataBinding> : DialogFragment() {
    protected lateinit var binding: TDataBinding

    abstract fun bind(container: ViewGroup?): TDataBinding
    abstract fun onBinding(dataBinding: TDataBinding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = bind(container)
        binding.lifecycleOwner = viewLifecycleOwner
        onBinding(binding)
        return binding.root
    }
}
