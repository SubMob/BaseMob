/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

package com.github.mustafaozhan.basemob.bottomsheet

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.github.mustafaozhan.basemob.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.android.support.AndroidSupportInjection

abstract class BaseBottomSheetDialogFragment : AppCompatDialogFragment() {

    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.BottomSheetDialogStyle)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        bottomSheetDialog = BottomSheetDialog(requireContext(), theme)
        return bottomSheetDialog
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    fun dismissDialog() {
        findNavController().navigateUp()
    }
}
