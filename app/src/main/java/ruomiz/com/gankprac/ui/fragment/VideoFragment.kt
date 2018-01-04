package ruomiz.com.gankprac.ui.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_video.*
import ruomiz.com.gankprac.R
import ruomiz.com.gankprac.adapter.VideoAdapter
import ruomiz.com.gankprac.base.BaseFragment
import ruomiz.com.gankprac.bean.ItemListBean
import ruomiz.com.gankprac.bean.Result
import ruomiz.com.gankprac.bean.VideoBean
import ruomiz.com.gankprac.mvp.contract.VideoContract
import ruomiz.com.gankprac.mvp.model.VideoModel
import ruomiz.com.gankprac.mvp.presenter.VideoPresenter
import java.util.regex.Pattern

/**
 * Ruomiz on 2017/12/28.
 * Time  waits  for  none
 * desc ： 视频界面
 */
class VideoFragment : BaseFragment(), VideoContract.View, SwipeRefreshLayout.OnRefreshListener {

    var mPresenter: VideoContract.Presenter? = null
    var mList = ArrayList<ItemListBean>()
    var isRefresh: Boolean = false
    var mAdapter: VideoAdapter? = null
    var date: String? = null

    override fun layoutRes(): Int {
        return R.layout.fragment_video
    }

    override fun initView() {
        mPresenter = VideoPresenter(context!!, this)
        mPresenter?.loadData()
        video_recycler.layoutManager = LinearLayoutManager(context)
        mAdapter = VideoAdapter(context!!, mList)
        video_recycler.adapter = mAdapter
        video_refresh.setOnRefreshListener(this)
        video_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                var layoutmanager: LinearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager;
                val position = layoutmanager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && position == mList.size - 1) {
                    date?.let { mPresenter?.loadMore(it) }
                }
            }
        })
    }

    override fun addData(videoBean: VideoBean?) {
        val regex = "[^0-9]"  //匹配不包括0-9的字符串
        val p = Pattern.compile(regex)
        val m = p.matcher(videoBean?.nextPageUrl)
        val s = m.replaceAll("");
        date = s.subSequence(1, s.length - 1).toString()

        if (isRefresh) {
            isRefresh = false
            video_refresh.isRefreshing = false
            if (mList.size > 0) {
                mList.clear()
            }
        }

        videoBean?.issueList!!
                .flatMap { it.itemList }
                .filter { it.type.equals("video") }
                .forEach { mList.add(it) }
        Logger.e("ruomiz"+mList.size)

        mAdapter?.notifyDataSetChanged()
    }

    override fun onRefresh() {
        if (!isRefresh) {
            isRefresh = true
           mPresenter?.loadData()
        }
    }

    companion object {
        fun newInstance(): VideoFragment {
            val fragment = VideoFragment()
            return fragment
        }
    }
}