package com.cogoport.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import com.cogoport.R
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setSupportActionBar(toolbar)
        val bitmapWidth = intent.getStringExtra("com.cogoport.adapter")

        webView = findViewById(R.id.webview) as WebView



        webView.loadUrl(bitmapWidth)
    }

}
