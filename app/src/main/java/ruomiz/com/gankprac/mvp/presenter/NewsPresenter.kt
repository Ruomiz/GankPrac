package ruomiz.com.gankprac.mvp.presenter

import android.content.Context
import io.reactivex.Observable
import ruomiz.com.gankprac.bean.NewsBean
import ruomiz.com.gankprac.mvp.contract.NewsContract
import ruomiz.com.gankprac.mvp.model.NewsModel
import ruomiz.com.gankprac.util.rxSchedulers

/**
 * Ruomiz on 2018/1/2.
 * Time  waits  for  none
 * desc ： 新闻页面
 */
class NewsPresenter(context: Context?, view: NewsContract.View) : NewsContract.Presenter {
    var mContext: Context? = null
    var mView: NewsContract.View? = null
    val mModel: NewsModel by lazy {
        NewsModel()
    }

    init {
        mContext = context
        mView = view
    }

    
    override fun loadData() {
        initData()
    }

    override fun initData() {
     val observable : Observable<NewsBean>? =  mContext?.let {mModel.loadData(it,false) }
        observable?.rxSchedulers()?.subscribe({ newBean: NewsBean? ->
            mView?.addData(newBean!!)
        })
    }

    fun loadMore() {
        val observable : Observable<NewsBean>? =  mContext?.let {mModel.loadData(it,false) }
        observable?.rxSchedulers()?.subscribe({ newBean: NewsBean? ->
            mView?.addData(newBean!!)
        })

    }

}