package ruomiz.com.gankprac.base

import android.app.Application
import android.content.Context
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.Logger.addLogAdapter
import zlc.season.rxdownload3.core.DownloadConfig


/**
 * Ruomiz on 2017/12/28.
 * Time  waits  for  none
 * desc ： app
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())
        val builder = DownloadConfig.Builder.create(this)
                .enableDb(true)
                .enableNotification(true)

        DownloadConfig.init(builder)
    }



}