/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.basemob.view.activity

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.github.mustafaozhan.basemob.R
import dagger.android.AndroidInjection

@Suppress("unused")
abstract class BaseActivity : AppCompatActivity() {

    @IdRes
    protected var containerId: Int = R.id.content

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    protected fun setHomeAsUpEnabled(enabled: Boolean) =
        supportActionBar?.setDisplayHomeAsUpEnabled(enabled)

    protected fun navigate(navDirections: NavDirections) =
        findNavController(containerId).navigate(
            navDirections, NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setEnterAnim(R.anim.enter_from_right)
            .setExitAnim(R.anim.exit_to_left)
            .setPopEnterAnim(R.anim.enter_from_left)
            .setPopExitAnim(R.anim.exit_to_right)
            .build()
        )
}
