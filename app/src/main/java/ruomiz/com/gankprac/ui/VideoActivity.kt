package ruomiz.com.gankprac.ui

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.listener.StandardVideoAllCallBack
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer
import kotlinx.android.synthetic.main.activity_videodetail.*
import ruomiz.com.gankprac.R
import ruomiz.com.gankprac.bean.VideoDetailBean
import ruomiz.com.gankprac.util.ImageUtils
import ruomiz.com.gankprac.util.VideoPlayerCallBack
import zlc.season.rxdownload3.core.DownloadConfig.context

/**
 * Ruomiz on 2018/1/2.
 * Time  waits  for  none
 * desc ： 视频详情
 */
class VideoActivity : AppCompatActivity() {
    lateinit var detailBean: VideoDetailBean
    lateinit var orientationUtils: OrientationUtils
    var isPause: Boolean = false
    var isPlay: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videodetail)

        detailBean = intent.getParcelableExtra<VideoDetailBean>("video")
        initView()
    }

    private fun initView() {
        val blurred = detailBean.blurred;
        blurred?.let { ImageUtils.display(this, blurred, iv_blurred) }
        tv_detail_title?.text = detailBean.title
        tv_detail_category?.text = "#${detailBean.category}"
        tv_detail_desv?.text = detailBean.description

        gsy_player.setUp(detailBean.playUrl, false, null, null)
        val imageView = ImageView(this)
        ImageUtils.display(this, detailBean.photo, imageView)
        gsy_player.thumbImageView = imageView
        orientationUtils = OrientationUtils(this, gsy_player)
        gsy_player.setIsTouchWiget(false)
        gsy_player.isRotateViewAuto = false
        gsy_player.isLockLand = false
        gsy_player.isShowFullAnimation = false
        gsy_player.isNeedLockFull = true
        gsy_player.fullscreenButton.setOnClickListener {
            //直接横屏
            orientationUtils.resolveByClick();
            //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
            gsy_player.startWindowFullscreen(this, true, true);
        }
        gsy_player.setStandardVideoAllCallBack(object : VideoPlayerCallBack() {
            override fun onPrepared(url: String?, vararg objects: Any?) {
                super.onPrepared(url, *objects)
                //开始播放了才能旋转和全屏
                orientationUtils.isEnable = true
                isPlay = true;
            }

            override fun onQuitFullscreen(url: String?, vararg objects: Any?) {
                super.onQuitFullscreen(url, *objects)
                orientationUtils?.let { orientationUtils.backToProtVideo() }
            }
        })
        gsy_player.setLockClickListener { view, lock ->
            orientationUtils.isEnable = !lock
        }
        gsy_player.backButton.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
    }


    override fun onBackPressed() {
        orientationUtils?.let { orientationUtils.backToProtVideo() }

        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return
        }
        super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        isPause = false
    }

    override fun onPause() {
        super.onPause()
        isPause = true
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoPlayer.releaseAllVideos()
        orientationUtils?.let { orientationUtils.releaseListener() }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (isPlay && !isPause) {
            if (newConfig?.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                if (!gsy_player.isIfCurrentIsFullscreen) {
                    gsy_player.startWindowFullscreen(context, true, true)
                }
            } else {
                if (gsy_player.isIfCurrentIsFullscreen) {
                    StandardGSYVideoPlayer.backFromWindowFull(this);
                }
                orientationUtils?.let { orientationUtils.isEnable = true }
            }
        }
    }
}