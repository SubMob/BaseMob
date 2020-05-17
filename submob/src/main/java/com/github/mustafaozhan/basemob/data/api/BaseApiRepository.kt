/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.basemob.data.api

import com.github.mustafaozhan.basemob.error.InternetConnectionException
import com.github.mustafaozhan.basemob.error.ModelMappingException
import com.github.mustafaozhan.basemob.error.NetworkException
import com.github.mustafaozhan.basemob.error.RetrofitException
import com.github.mustafaozhan.basemob.error.UnknownNetworkException
import com.github.mustafaozhan.basemob.model.Result
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
                Result.Success(suspendBlock.invoke())
            } catch (e: Throwable) {
                Result.Error(
                    when (e) {
                        is CancellationException -> e
                        is UnknownHostException,
                        is TimeoutException,
                        is IOException,
                        is SSLException -> NetworkException(e)
                        is ConnectException -> InternetConnectionException(e)
                        is JsonDataException -> ModelMappingException(e)
                        is HttpException -> RetrofitException(
                            e.response()?.code().toString() + " " + e.response()?.message(),
                            e.response(),
                            e
                        )
                        else -> UnknownNetworkException(e)
                    }
                )
            }
        }
}
