package ruomiz.com.gankprac.api

import android.content.Context
import com.orhanobut.logger.Logger
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Ruomiz on 2017/12/28.
 * Time  waits  for  none
 * desc ： xxxx
 */
class RetrofitService private constructor(context: Context,baseUrl:String) {
    var okHttpClient: OkHttpClient? = null
    var retrofit: Retrofit? = null
    var cache: Cache? = null
    val DEFAULT_TIMEOUT: Long = 20
    var httpCacheDirectory: File? = null
    var mContext : Context  = context

    val url = baseUrl
    init {

        //缓存地址
        if (httpCacheDirectory == null) {
            httpCacheDirectory = File(mContext.cacheDir, "app_cache")
        }
        try {
            if (cache == null) {
                cache = Cache(httpCacheDirectory, 10 * 1024 * 1024)
            }
        } catch (e: Exception) {
            Logger.d("OKHttp", "Could not create http cache", e)
        }
        okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cache(cache)
                .addInterceptor(HttpLoggingInterceptor())
                .addInterceptor(CacheControlInterceptor(mContext))
                .addNetworkInterceptor(CacheControlInterceptor(mContext))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)//全局读取超时时间
                .build()
        //创建retrofit

        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build()
    }

    companion object {
//        @Volatile
        var instance: RetrofitService? = null

        fun getInstance(context: Context, baseUrl: String): RetrofitService {
//            if (instance == null) {
//                synchronized(RetrofitService::class) {
//                    if (instance == null) {
                        instance = RetrofitService(context,baseUrl)
//                    }
//                }
//            }
            return instance!!
        }
    }

    fun <T> create(service: Class<T>?): T? {
        if (service == null) {
            throw RuntimeException("Api service is null!")
        }
        return retrofit?.create(service)
    }
}




