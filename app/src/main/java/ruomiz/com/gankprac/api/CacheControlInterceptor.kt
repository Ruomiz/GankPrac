package ruomiz.com.gankprac.api

import android.content.Context
import android.util.Log
import com.orhanobut.logger.Logger
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import ruomiz.com.gankprac.base.App
import ruomiz.com.gankprac.util.NetworkUtils

/**
 * Ruomiz on 2017/12/28.
 * Time  waits  for  none
 * desc ： xxxx
 */
class CacheControlInterceptor(context: Context) : Interceptor {
    val context : Context  = context
    override fun intercept(chain: Interceptor.Chain?): Response? {
        var request = chain?.request()
        if (NetworkUtils.isNetConneted(context)) {
            val response = chain?.proceed(request)
            // read from cache for 60 s
            val maxAge = 60
            val cacheControl = request?.cacheControl().toString()
            Logger.d("CacheInterceptor", "6s load cahe" + cacheControl)
            return response?.newBuilder()?.removeHeader("Pragma")?.removeHeader("Cache-Control")?.header("Cache-Control", "public, max-age=" + maxAge)?.build()
        } else {
            Logger.d("CacheInterceptor", " no network load cahe")
            request = request?.newBuilder()?.cacheControl(CacheControl.FORCE_CACHE)?.build()
            val response = chain?.proceed(request)
            //set cahe times is 3 days
            val maxStale = 60 * 60 * 24 * 3
            return response?.newBuilder()?.removeHeader("Pragma")?.removeHeader("Cache-Control")?.header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)?.build()
        }
    }
}