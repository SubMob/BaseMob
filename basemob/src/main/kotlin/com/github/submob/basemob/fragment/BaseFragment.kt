/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.basemob.fragment

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.github.submob.basemob.R
import com.github.submob.basemob.activity.BaseActivity

@Suppress("unused")
open class BaseFragment : Fragment() {
    protected fun getBaseActivity() = activity as? BaseActivity

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
                        .setPopUpTo(options.popUpToId, options.isPopUpToInclusive())
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
