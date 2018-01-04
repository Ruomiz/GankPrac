package ruomiz.com.gankprac.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ruomiz.com.gankprac.bean.VideoBean

/**
 * Ruomiz on 2017/12/28.
 * Time  waits  for  none
 * desc ： kaiyan Api
 */
interface EyepetizerApi {

    //获取首页第一页数据
    @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getHomeData(): Observable<VideoBean>

    //获取首页第一页之后的数据  ?date=1499043600000&num=2
    @GET("v2/feed/")
    fun getHomeMoreData(@Query("date") date: String, @Query("num") num: Int): Observable<VideoBean>
}