/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.basemob.error

import retrofit2.Response

@Suppress("UNUSED_PARAMETER", "unused")
class RetrofitException(
    override val message: String?,
    val url: String?,
    val response: Response<*>,
    override val cause: Throwable
) : Throwable(cause)
