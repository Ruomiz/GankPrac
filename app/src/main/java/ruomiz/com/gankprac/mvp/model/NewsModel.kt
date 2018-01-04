package ruomiz.com.gankprac.mvp.model

import android.content.Context
import io.reactivex.Observable
import ruomiz.com.gankprac.api.GankApi
import ruomiz.com.gankprac.api.RetrofitService
import ruomiz.com.gankprac.bean.NewsBean
import ruomiz.com.gankprac.constant.ResConstants

/**
 * Ruomiz on 2018/1/2.
 * Time  waits  for  none
 * desc ： newsModel
 */
class NewsModel {
    var page: Int = 0
    fun loadData(context: Context, loadMore: Boolean): Observable<NewsBean>? {
        val retrofitService = RetrofitService.getInstance(context, ResConstants.GANK_URL)
        val apiService = retrofitService.create(GankApi::class.java)
        if (page < 11) page++ else page = 10

        when (loadMore) {
            true -> return apiService?.getNews(page)
            else -> return apiService?.getNews(1)
        }


    }
}