/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.basemob.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

@Suppress("unused")
abstract class BaseActivity : AppCompatActivity() {

    protected abstract var containerId: Int

    protected fun getNavigationController() = findNavController(containerId)
}
