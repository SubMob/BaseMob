/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.basemob.preferences

import com.squareup.moshi.Moshi

interface BasePreferencesRepository {
    val preferencesFactory: BasePreferencesFactory
    val moshi: Moshi
}

