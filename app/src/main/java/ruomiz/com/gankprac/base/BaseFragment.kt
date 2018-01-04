package ruomiz.com.gankprac.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Ruomiz on 2017/12/28.
 * Time  waits  for  none
 * desc ： BaseFragment
 */
abstract class BaseFragment : Fragment() {
    open protected var isVisibled: Boolean = false
    var rootView: View? = null
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            isVisibled = true
        }
        if (rootView==null){
            return
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater?.inflate(layoutRes(), container, false)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    abstract fun initView()

    abstract fun layoutRes(): Int
}