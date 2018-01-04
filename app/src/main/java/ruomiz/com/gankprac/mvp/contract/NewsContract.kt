package ruomiz.com.gankprac.mvp.contract

import ruomiz.com.gankprac.base.BasePresenter
import ruomiz.com.gankprac.base.BaseView
import ruomiz.com.gankprac.bean.NewsBean

/**
 * Ruomiz on 2018/1/2.
 * Time  waits  for  none
 * desc ： xxxx
 */
interface NewsContract{

    interface View : BaseView<Presenter>{
        fun addData(newBean: NewsBean)

    }
    interface Presenter : BasePresenter{
         fun initData()
    }
}