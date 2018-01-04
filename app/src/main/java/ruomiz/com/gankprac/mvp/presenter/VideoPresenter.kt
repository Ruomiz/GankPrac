package ruomiz.com.gankprac.mvp.presenter

import android.content.Context
import android.telecom.InCallService
import android.view.LayoutInflater
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import ruomiz.com.gankprac.bean.VideoBean
import ruomiz.com.gankprac.mvp.contract.VideoContract
import ruomiz.com.gankprac.mvp.model.VideoModel
import ruomiz.com.gankprac.util.rxSchedulers
import zlc.season.rxdownload3.core.DownloadConfig.context

/**
 * Ruomiz on 2017/12/28.
 * Time  waits  for  none
 * desc ： 视频
 */
class VideoPresenter(context: Context, view: VideoContract.View) : VideoContract.Presenter {

    var mView: VideoContract.View? = null
    var mContext: Context? = null
    var inflater: LayoutInflater? = null
    val mModel: VideoModel by lazy {
        VideoModel()
    }

    init {
        mView = view
        mContext = context
        inflater = LayoutInflater.from(context)
    }

    override fun loadData() {
        var observable: Observable<VideoBean>? = mContext?.let { mModel.loadMore(it, false, "") }
        observable?.rxSchedulers()?.subscribe { videoBean: VideoBean? ->
            mView?.addData(videoBean)
            Logger.e(videoBean.toString())
        }
    }

    override fun loadMore(date: String) {
        var observable: Observable<VideoBean>? = mContext?.let { mModel.loadMore(it, true, date) }
        observable?.rxSchedulers()?.subscribe { videoBean: VideoBean? ->
            mView?.addData(videoBean)
            Logger.e(videoBean.toString())
        }
    }

}