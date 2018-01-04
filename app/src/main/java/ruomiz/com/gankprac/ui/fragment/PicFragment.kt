package ruomiz.com.gankprac.ui.fragment

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_pic.*
import kotlinx.android.synthetic.main.fragment_video.*
import ruomiz.com.gankprac.R
import ruomiz.com.gankprac.adapter.PicAdapter
import ruomiz.com.gankprac.base.BaseFragment
import ruomiz.com.gankprac.bean.PictureBean
import ruomiz.com.gankprac.bean.Result
import ruomiz.com.gankprac.mvp.contract.PicContract
import ruomiz.com.gankprac.mvp.presenter.PicPresenter

/**
 * Ruomiz on 2017/12/28.
 * Time  waits  for  none
 * desc ： 图片
 */
class PicFragment : BaseFragment(), PicContract.View, SwipeRefreshLayout.OnRefreshListener {


    var mPresenter: PicPresenter? = null
    var mAdapter: PicAdapter? = null
    var mList = ArrayList<Result>()
    var isRefresh: Boolean = false

    override fun layoutRes(): Int {
        return R.layout.fragment_pic
    }

    override fun initView() {
        mPresenter = PicPresenter(context!!, this)
        mPresenter?.loadData()
        pic_recycler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mAdapter = PicAdapter(context!!, mList)
        pic_recycler.adapter = mAdapter
        pic_refresh.setOnRefreshListener(this)
        pic_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutmanager: StaggeredGridLayoutManager = recyclerView?.layoutManager as StaggeredGridLayoutManager;
                val lastPostion = IntArray(layoutmanager.spanCount)
                val position = layoutmanager.findLastVisibleItemPositions(lastPostion)
                val lastVisiblePostion = lastPostion.max()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisiblePostion == mList.size - 1) {
                    mPresenter?.loadMore()
                }
            }
        })
    }

    override fun onRefresh() {
        if (!isRefresh) {
            isRefresh = true
            mPresenter?.loadData()
        }
    }

    override fun addData(bean: PictureBean?) {
        if (isRefresh) {
            isRefresh = false
            pic_refresh.isRefreshing = false
            if (mList.size > 0) {
                mList.clear()
            }
        }

        bean?.results?.forEach {
            mList.add(it)
        }
        mAdapter?.notifyDataSetChanged()

    }

    companion object {
        fun newInstance(): PicFragment {
            val fragment = PicFragment()
            return fragment
        }
    }
}