package ruomiz.com.gankprac.util

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import io.reactivex.Observable

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Ruomiz on 2017/12/28.
 * Time  waits  for  none
 * desc ： xxxx
 */
fun Context.showToast(message: String): Toast {
    var toast: Toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
    return toast
}

fun <T> Observable<T>.rxSchedulers(): Observable<T> {

    return subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}