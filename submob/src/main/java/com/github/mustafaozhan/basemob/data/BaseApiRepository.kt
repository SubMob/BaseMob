/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.basemob.data

import com.github.mustafaozhan.basemob.error.InternetConnectionException
import com.github.mustafaozhan.basemob.error.ModelMappingException
import com.github.mustafaozhan.basemob.error.NetworkException
import com.github.mustafaozhan.basemob.error.RetrofitException
import com.github.mustafaozhan.basemob.error.UnknownNetworkException
import com.github.mustafaozhan.basemob.model.Result
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLException

abstract class BaseApiRepository {

    protected abstract val endpoint: String
    protected abstract val timeOut: Long

    protected fun createRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endpoint)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .client(httpClient)
            .build()
    }

    protected fun getClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(timeOut, TimeUnit.SECONDS)
        .readTimeout(timeOut, TimeUnit.SECONDS)
        .writeTimeout(timeOut, TimeUnit.SECONDS)
        .addInterceptor(getInterceptor())
        .build()

    private fun getInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

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
