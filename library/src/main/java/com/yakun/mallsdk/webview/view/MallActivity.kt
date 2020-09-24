package com.yakun.mallsdk.webview.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.yakun.mallsdk.R
import com.yakun.mallsdk.webview.inter.InterWebListener
import com.yakun.mallsdk.webview.tools.AndroidBug5497Workaround
import com.yakun.mallsdk.webview.utils.X5WebUtils
import com.yakun.mallsdk.webview.widget.WebProgress

class MallActivity : AppCompatActivity() {

    private lateinit var mWebView: X5WebView
    private lateinit var mProgress: WebProgress
    private var workaround: AndroidBug5497Workaround? = null
    private val url = "https://m.baidu.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mall)
        mWebView = findViewById(R.id.web_view)
        mProgress = findViewById(R.id.web_progress)

        mProgress.show()
        mProgress.setColor(Color.parseColor("#FF4081"), Color.parseColor("#3F51B5"))

        mWebView.loadUrl(url)
        mWebView.x5WebChromeClient.setWebListener(interWebListener)
        mWebView.x5WebViewClient.setWebListener(interWebListener)

        workaround = AndroidBug5497Workaround(this)
    }

    private val interWebListener = object : InterWebListener {
        override fun hindProgressBar() {
            mProgress.hide()
        }

        override fun showErrorView(type: Int) {
            when (type) {
                //没有网络
                X5WebUtils.ErrorMode.NO_NET -> {
                }
                //404，网页无法打开
                X5WebUtils.ErrorMode.STATE_404 -> {
                }
                //onReceivedError，请求网络出现error
                X5WebUtils.ErrorMode.RECEIVED_ERROR -> {
                }
                //在加载资源时通知主机应用程序发生SSL错误
                X5WebUtils.ErrorMode.SSL_ERROR -> {
                }
                else -> {
                }
            }
        }

        override fun startProgress(newProgress: Int) {
            mProgress.setProgress(newProgress)
        }

        override fun showTitle(title: String?) {

        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onResume() {
        super.onResume()
        mWebView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mWebView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mWebView.settings.javaScriptEnabled = false
    }

    override fun onDestroy() {
        mWebView.destroy()
        workaround?.onDestroy()
        super.onDestroy()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (event?.keyCode == KeyEvent.KEYCODE_BACK && event.repeatCount == 0) {
            if (mWebView.pageCanGoBack()) {
                //退出网页
                return mWebView.pageGoBack()
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}