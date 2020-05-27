/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.basemob.data.preferences

import com.squareup.moshi.Moshi

interface BasePreferencesRepository {
    val preferencesFactory: BasePreferencesFactory
    val moshi: Moshi
}
