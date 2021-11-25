package com.myapp.spaceflightapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.myapp.spaceflightapp.presentation.Progress

interface WebViewInterface {
    fun initSettings(view: View, context: Context)
    fun loadWebView(data: String, view: View, context: Context)

    class Base : WebViewInterface {
        private var progress: Progress? = null
        private var isLoaded: Boolean = false

        @SuppressLint("SetJavaScriptEnabled")
        override fun initSettings(view: View, context: Context) {
            val settings = view.findViewById<WebView>(R.id.webView).settings
            settings.javaScriptEnabled = true
            settings.allowFileAccess = true
            settings.domStorageEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.supportMultipleWindows()
            progress = Progress(context, R.string.please_wait, cancelable = true)
        }

        override fun loadWebView(data: String, view: View, context: Context) {
            showProgress(true)
            val webView = view.findViewById<WebView>(R.id.webView)
            webView.visibility = View.VISIBLE
            webView.loadUrl(data)
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                   val url = request?.url.toString()
                        view?.loadUrl(url)
                    return super.shouldOverrideUrlLoading(view, request)
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    showProgress(true)
                    super.onPageStarted(view, url, favicon)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    isLoaded = true
                    showProgress(false)
                    super.onPageFinished(view, url)
                }

                override fun onReceivedError(
                    view: WebView,
                    request: WebResourceRequest,
                    error: WebResourceError
                ) {
                    isLoaded = false
                    val errorMessage = "Got Error! $error"
                    showToast(errorMessage, context)
                    showProgress(false)
                    super.onReceivedError(view, request, error)
                }
            }
        }

        private fun showToast(message: String, context: Context) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        fun showProgress(visible: Boolean) {
            progress?.apply { if (visible) show() else dismiss() }
        }
    }
}