package ruomiz.com.gankprac.ui.fragment

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_news.*
import ruomiz.com.gankprac.R
import ruomiz.com.gankprac.adapter.NewsAdapter
import ruomiz.com.gankprac.base.BaseFragment
import ruomiz.com.gankprac.bean.NewsBean
import ruomiz.com.gankprac.bean.Result
import ruomiz.com.gankprac.mvp.contract.NewsContract
import ruomiz.com.gankprac.mvp.presenter.NewsPresenter

/**
 * Ruomiz on 2017/12/28.
 * Time  waits  for  none
 * desc ： 新闻热点
 */
class NewsFragment : BaseFragment(), NewsContract.View, SwipeRefreshLayout.OnRefreshListener {
    var isRefresh: Boolean = false
    var mPresenter: NewsPresenter? = null
    var mAdapter: NewsAdapter? = null
    var mList = ArrayList<Result>()

    override fun layoutRes(): Int {
        return R.layout.fragment_news
    }

    override fun initView() {
        mPresenter = NewsPresenter(context, this)
        mPresenter?.loadData()
        news_recyclerview.layoutManager = LinearLayoutManager(context)
        mAdapter = NewsAdapter(context, mList)
        news_recyclerview.adapter = mAdapter
        refreshlayout.setOnRefreshListener(this)
        news_recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager: LinearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
                var lastPosition = layoutManager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPosition == mList?.size - 1) {
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

    override fun addData(newBean: NewsBean) {
        if (isRefresh) {
            isRefresh = false
            refreshlayout.isRefreshing = false
            if (mList.size > 0) {
                mList.clear()
            }
        }

        newBean.results.forEach {
            mList.add(it)
        }
        mAdapter?.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): NewsFragment {
            val fragment = NewsFragment()
            return fragment
        }
    }
}