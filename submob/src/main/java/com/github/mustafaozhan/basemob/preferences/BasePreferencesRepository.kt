/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.basemob.preferences

import com.squareup.moshi.Moshi

abstract class BasePreferencesRepository {
    protected abstract val preferencesHelper: BasePreferencesHelper
    protected abstract val moshi: Moshi
}

