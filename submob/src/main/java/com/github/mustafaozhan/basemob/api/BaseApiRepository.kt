/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.basemob.api

import com.github.mustafaozhan.basemob.error.InternetConnectionException
import com.github.mustafaozhan.basemob.error.ModelMappingException
import com.github.mustafaozhan.basemob.error.NetworkException
import com.github.mustafaozhan.basemob.error.RetrofitException
import com.github.mustafaozhan.basemob.error.UnknownNetworkException
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLException

abstract class BaseApiRepository {

    abstract val apiFactory: BaseApiFactory

    @Suppress("ThrowsCount", "TooGenericExceptionCaught")
    suspend fun <T> apiRequest(suspendBlock: suspend () -> T) =
        withContext(Dispatchers.IO) {
            try {
                suspendBlock.invoke()
            } catch (e: Throwable) {
                when (e) {
                    is CancellationException -> throw e
                    is UnknownHostException,
                    is TimeoutException,
                    is IOException,
                    is SSLException -> throw NetworkException(e)
                    is ConnectException -> throw InternetConnectionException(e)
                    is JsonDataException -> throw ModelMappingException(e)
                    is HttpException -> throw RetrofitException(
                        e.response()?.code().toString() + " " + e.response()?.message(),
                        e.response(),
                        e
                    )
                    else -> throw UnknownNetworkException(e)
                }
            }
        }
}
