package ruomiz.com.gankprac.mvp.contract

import ruomiz.com.gankprac.base.BasePresenter
import ruomiz.com.gankprac.base.BaseView
import ruomiz.com.gankprac.bean.VideoBean

/**
 * Ruomiz on 2018/1/3.
 * Time  waits  for  none
 * desc ： 视频
 */
interface VideoContract {
    interface View : BaseView<Presenter> {
        fun addData(videoBean: VideoBean?)
    }

    interface Presenter : BasePresenter {
         fun loadMore(date : String)
    }
}