package ruomiz.com.gankprac.ui

import android.Manifest
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.github.chrisbanes.photoview.PhotoView
import io.reactivex.android.schedulers.AndroidSchedulers
import ruomiz.com.gankprac.R
import ruomiz.com.gankprac.util.ImageUtils
import zlc.season.rxdownload3.RxDownload
import zlc.season.rxdownload3.core.Mission
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.widget.Toast
import com.tbruyelle.rxpermissions2.RxPermissions
import ruomiz.com.gankprac.util.showToast
import zlc.season.rxdownload3.core.Status
import zlc.season.rxdownload3.core.Succeed
import java.io.File


/**
 * Ruomiz on 2018/1/2.
 * Time  waits  for  none
 * desc ： 图片详情
 */
class ImageDetailActivity : AppCompatActivity() {

    private var currentStatu = Status()
    var url: String = "url"
    var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagedetail)

        val imageUrl = intent.getStringExtra("image")
        url = imageUrl
        val photoView = findViewById<PhotoView>(R.id.iv_detail)
        ImageUtils.display(this, imageUrl, photoView)

        Snackbar.make(photoView, "", Snackbar.LENGTH_LONG).setAction("下载", {
            checkPermissions()
        }).show()
    }

    private fun checkPermissions() {
        val rxpermission = RxPermissions(this)
        rxpermission.request(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe { t: Boolean? ->
                    if (t!!) {
                        downLoadImage()
                    }
                }

    }

    private fun downLoadImage() {
        val position = url.lastIndexOf("/");
        val saveName: String = url.substring(position + 1, url.length - 1)
        val savePath: String = Environment.getExternalStorageDirectory().path + "/gank"
        val picfile = File(savePath)
        if (!picfile.exists()) {
            picfile.mkdir()
        }
        val file = File(picfile, saveName)
        val mission = Mission(url, saveName, savePath)

        RxDownload.create(mission)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { status ->
                    //开始下载
                    currentStatu = status
                    RxDownload.start(mission).subscribe()
                    setActionText(status)
                }

        try {
            MediaStore.Images.Media.insertImage(getContentResolver(), file.absolutePath, saveName, null)
            sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.absolutePath)));
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun setActionText(status: Status?) {
        val text = when (status) {
            is Succeed -> "下载成功"
            else -> ""
        }
        if (!TextUtils.isEmpty(text)) {
            toast = showToast(text)
        }
    }

    override fun onPause() {
        super.onPause()
        RxDownload.stop(url).subscribe()
        toast?.cancel()
    }
}
