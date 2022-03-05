/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

package com.github.mustafaozhan.basemob.bottomsheet

import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.github.mustafaozhan.basemob.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

abstract class BaseBottomSheetDialogFragment : AppCompatDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogStyle)
    }

    override fun onCreateDialog(
        savedInstanceState: Bundle?
    ) = BottomSheetDialog(
        requireContext(),
        theme
    ).apply {
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    protected fun dismissDialog() = findNavController().navigateUp()

    protected fun navigate(
        currentDestinationId: Int,
        navDirections: NavDirections,
        animate: Boolean = true
    ) = findNavController().let {
        if (it.currentDestination?.id == currentDestinationId) {
            val navOptionsBuilder = it.graph
                .findNode(currentDestinationId)
                ?.getAction(navDirections.actionId)
                ?.navOptions?.let { options ->
                    NavOptions.Builder()
                        .setLaunchSingleTop(options.shouldLaunchSingleTop())
                        .setPopUpTo(options.popUpTo, options.isPopUpToInclusive)
                } ?: NavOptions.Builder()

            if (animate) {
                navOptionsBuilder
                    .setEnterAnim(R.anim.enter_from_right)
                    .setExitAnim(R.anim.exit_to_left)
                    .setPopEnterAnim(R.anim.enter_from_left)
                    .setPopExitAnim(R.anim.exit_to_right)
            }

            it.navigate(navDirections, navOptionsBuilder.build())
        }
    }
}
