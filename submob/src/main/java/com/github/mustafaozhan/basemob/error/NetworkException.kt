/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.basemob.error

open class NetworkException(cause: Throwable) : Throwable(cause)

class UnknownNetworkException(cause: Throwable) : NetworkException(cause)

class ModelMappingException(cause: Throwable) : NetworkException(cause)

class EmptyParameterException : NetworkException(Exception("parameter can not be empty"))
