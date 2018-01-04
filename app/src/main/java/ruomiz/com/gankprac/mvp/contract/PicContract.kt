package ruomiz.com.gankprac.mvp.contract

import ruomiz.com.gankprac.base.BasePresenter
import ruomiz.com.gankprac.base.BaseView
import ruomiz.com.gankprac.bean.PictureBean

/**
 * Ruomiz on 2018/1/2.
 * Time  waits  for  none
 * desc ： 图片
 */
interface PicContract {
    interface View : BaseView<Presenter> {
        fun addData(bean: PictureBean?)

    }

    interface Presenter : BasePresenter {

    }
}