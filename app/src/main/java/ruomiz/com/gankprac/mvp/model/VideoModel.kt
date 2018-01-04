package ruomiz.com.gankprac.mvp.model

import android.content.Context
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import ruomiz.com.gankprac.api.EyepetizerApi
import ruomiz.com.gankprac.api.GankApi
import ruomiz.com.gankprac.api.RetrofitService
import ruomiz.com.gankprac.bean.VideoBean
import ruomiz.com.gankprac.constant.ResConstants

/**
 * Ruomiz on 2018/1/2.
 * Time  waits  for  none
 * desc ： 开眼视频数据
 */
class VideoModel {
    fun loadMore(mContext: Context, loadMore: Boolean, date: String): Observable<VideoBean>? {
        val retrofitService = RetrofitService.getInstance(mContext, ResConstants.KAIYAN_URL);
        val apiService = retrofitService.create(EyepetizerApi::class.java);

        when (loadMore) {
            true -> return apiService?.getHomeMoreData(date, 2)
            else -> return apiService?.getHomeData()
        }
    }

}