/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

package com.github.mustafaozhan.basemob.view.bottomsheet

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
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
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogStyle)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        bottomSheetDialog = BottomSheetDialog(requireContext(), theme)
        return bottomSheetDialog
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun dismissDialog() = findNavController().navigateUp()

    protected fun navigate(
        currentDestinationId: Int,
        navDirections: NavDirections
    ) = findNavController().let {
        if (it.currentDestination?.id == currentDestinationId) {
            it.navigate(navDirections, NavOptions.Builder().setLaunchSingleTop(true).build())
            dismissDialog()
        }
    }
}
