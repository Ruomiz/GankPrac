package ruomiz.com.gankprac.mvp.model

import android.content.Context
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import ruomiz.com.gankprac.api.GankApi
import ruomiz.com.gankprac.api.RetrofitService
import ruomiz.com.gankprac.bean.PictureBean
import ruomiz.com.gankprac.constant.ResConstants

/**
 * Ruomiz on 2018/1/2.
 * Time  waits  for  none
 * desc ： PicModel
 */
class PicModel {
    var page: Int = 0
    fun loadMore(mContext: Context, loadMore: Boolean): Observable<PictureBean>? {
        val retrofitService = RetrofitService.getInstance(mContext, ResConstants.GANK_URL);
        val apiService = retrofitService.create(GankApi::class.java);
        if (page < 11) page++ else page = 10
        Logger.d("ruomiz" + page)
        when (loadMore) {
            true -> return apiService?.getPicture(page)
            else -> return apiService?.getPicture(1)
        }
    }

}