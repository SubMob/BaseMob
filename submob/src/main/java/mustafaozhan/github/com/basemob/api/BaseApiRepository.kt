package mustafaozhan.github.com.basemob.api

import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mustafaozhan.github.com.basemob.error.ModelMappingException
import mustafaozhan.github.com.basemob.error.NetworkException
import mustafaozhan.github.com.basemob.error.RetrofitException
import mustafaozhan.github.com.basemob.error.UnknownNetworkException
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLException

@Suppress("ThrowsCount", "TooGenericExceptionCaught")
abstract class BaseApiRepository {

    abstract val apiHelper: BaseApiHelper

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
                    is JsonDataException -> throw ModelMappingException(e)
                    is HttpException -> {
                        val message = e.response().code().toString() + " " + e.response().message()
                        val url = ""
                        //    e.response().raw().request.url.toString()
                        // todo need to pass url here
                        throw RetrofitException(message, url, e.response(), e)
                    }
                    else -> throw UnknownNetworkException(e)
                }
            }
        }
}
