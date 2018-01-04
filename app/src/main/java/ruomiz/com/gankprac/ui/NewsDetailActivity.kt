package ruomiz.com.gankprac.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_new_detail.*
import ruomiz.com.gankprac.R

/**
 * Ruomiz on 2018/1/4.
 * Time  waits  for  none
 * desc ： 新闻详情
 */
class NewsDetailActivity : AppCompatActivity() {
    var webUrl: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_detail)
        webUrl = intent.getStringExtra("url")

        initWebView()
    }

    private fun initWebView() {
        webview.loadUrl(webUrl)
        val setting = webview.settings
        setting.javaScriptEnabled = true
        setting.setSupportZoom(true)
    }

}