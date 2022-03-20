/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.basemob.activity

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.github.submob.basemob.R

@Suppress("unused")
abstract class BaseActivity : AppCompatActivity() {

    @IdRes
    protected var containerId: Int = R.id.content

    protected fun getNavigationController() = findNavController(containerId)
}
