/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.basemob.error

class SharedPreferencesException(
    message: String = "SharedPreferences does not support this type."
) : IllegalArgumentException(message)
