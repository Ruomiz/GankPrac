package ruomiz.com.gankprac.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Ruomiz on 2017/12/28.
 * Time  waits  for  none
 * desc ： xxxx
 */
class HomeViewPagerAdapter(f: FragmentManager) : FragmentPagerAdapter(f) {


    var fragments: MutableList<Fragment>? = null

     val titles = arrayOf("资讯","图片", "视频")


    override fun getItem(position: Int): Fragment {
        return fragments!![position]
    }

    override fun getCount(): Int {
        return fragments?.size ?: 0
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }


    fun addFragment(fragmentList: MutableList<Fragment>?) {
        this.fragments = fragmentList
        notifyDataSetChanged()
    }
}