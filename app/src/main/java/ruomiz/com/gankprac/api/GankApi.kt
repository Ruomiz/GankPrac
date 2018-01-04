package ruomiz.com.gankprac.api

import android.graphics.pdf.PdfDocument
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import ruomiz.com.gankprac.bean.NewsBean
import ruomiz.com.gankprac.bean.PictureBean
import ruomiz.com.gankprac.bean.VideoBean

/**
 * Ruomiz on 2017/12/28.
 * Time  waits  for  none
 * desc ： GankIo Api
 */
interface GankApi {

    @GET("data/Android/10/{page}")
    fun getNews(@Path("page") page: Int): Observable<NewsBean>

    @GET("data/福利/10/{page}")
    fun getPicture(@Path("page") page: Int): Observable<PictureBean>

}