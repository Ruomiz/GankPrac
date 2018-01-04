package ruomiz.com.gankprac.mvp.presenter

import android.content.Context
import android.graphics.Picture
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import ruomiz.com.gankprac.bean.PictureBean
import ruomiz.com.gankprac.bean.Result
import ruomiz.com.gankprac.mvp.contract.PicContract
import ruomiz.com.gankprac.mvp.model.PicModel
import ruomiz.com.gankprac.util.rxSchedulers

/**
 * Ruomiz on 2018/1/2.
 * Time  waits  for  none
 * desc ： PicPresenter
 */
class PicPresenter(context: Context, view: PicContract.View) : PicContract.Presenter {
    var mContext: Context? = null
    var mView: PicContract.View? = null
    var mList = ArrayList<Result>()

    val mModel: PicModel by lazy {
        PicModel()
    }

    init {
        mContext = context
        mView = view
    }

    override fun loadData() {
        var observable: Observable<PictureBean>? = mContext?.let { mModel.loadMore(it, false) }
        observable?.rxSchedulers()?.subscribe({ bean: PictureBean? ->
            Logger.e("ruomiz" + bean.toString())
            mView?.addData(bean)

        })

    }

    fun loadMore() {
        var observable: Observable<PictureBean>? = mContext?.let { mModel.loadMore(it, true) }
        observable?.rxSchedulers()?.subscribe({ bean: PictureBean? ->
            Logger.e("ruomiz" + bean.toString())
            mView?.addData(bean)
        })
    }

}