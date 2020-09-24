package com.yakun.mallsdk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.yakun.mallsdk.webview.view.MallActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.app_count_tv).text = "${AppCount.COUNT}"

        app_count_tv.setOnClickListener {
            startActivity(Intent(this, MallActivity::class.java))
        }
    }
}