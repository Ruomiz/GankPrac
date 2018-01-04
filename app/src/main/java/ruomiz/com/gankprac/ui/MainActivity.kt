package ruomiz.com.gankprac.ui

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import ruomiz.com.gankprac.R
import ruomiz.com.gankprac.adapter.HomeViewPagerAdapter
import ruomiz.com.gankprac.ui.fragment.NewsFragment
import ruomiz.com.gankprac.ui.fragment.PicFragment
import ruomiz.com.gankprac.ui.fragment.VideoFragment
import ruomiz.com.gankprac.util.showToast

/**
 * Ruomiz on 2017/12/28.
 * Time  waits  for  none
 * desc ： 主页
 */
class MainActivity : AppCompatActivity() {
    var mExitTime: Long = 0
    var mToast: Toast? = null
    lateinit var fragments: MutableList<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolBar()
        initFragment()
        initViewPager()
    }

    private fun initViewPager() {
        var adapter = HomeViewPagerAdapter(supportFragmentManager)
        viewpager.adapter = adapter
        adapter.addFragment(fragments)
        tablayout.setupWithViewPager(viewpager)
        tablayout.tabMode = TabLayout.MODE_FIXED

    }

    private fun initFragment() {
        fragments = ArrayList()
        fragments.add(NewsFragment.newInstance())
        fragments.add(PicFragment.newInstance())
        fragments.add(VideoFragment.newInstance())
    }

    private fun setToolBar() {
        setSupportActionBar(toolbar)
        var bar = supportActionBar
        bar?.title = "Gank"
        bar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mExitTime < 2000) {
                finish()
                System.exit(0)
                mToast?.cancel()
            } else {
                mExitTime = System.currentTimeMillis()
                mToast = showToast("再按一次退出程序")
            }
            return true
        }

        return super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
    }
}