package com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.payment

import android.annotation.SuppressLint
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun CheckoutThreeDSecureScreen(
    htmlContent: String?,
    redirectUrl: String?,
    onSuccess: () -> Unit,
    onFailure: () -> Unit,
    onBackClick: () -> Unit
) {
    BackHandler {
        onBackClick()
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true

                webViewClient =
                    object : WebViewClient() {
                        override fun shouldOverrideUrlLoading(
                            view: WebView?,
                            request: WebResourceRequest?
                        ): Boolean {
                            val url = request?.url ?: return false

                            if (
                                url.host.equals(
                                    "www.bulbulustur.com",
                                    ignoreCase = true
                                ) &&
                                url.path.equals(
                                    "/b2c/order/success",
                                    ignoreCase = true
                                )
                            ) {
                                onSuccess()
                                return true
                            }

                            if (
                                url.host.equals(
                                    "www.bulbulustur.com",
                                    ignoreCase = true
                                ) &&
                                url.path.equals(
                                    "/b2c/order/fail",
                                    ignoreCase = true
                                )
                            ) {
                                onFailure()
                                return true
                            }

                            return false
                        }
                    }

                when {
                    !htmlContent.isNullOrBlank() -> {
                        loadDataWithBaseURL(
                            null,
                            htmlContent,
                            "text/html",
                            "UTF-8",
                            null
                        )
                    }

                    !redirectUrl.isNullOrBlank() -> {
                        loadUrl(redirectUrl)
                    }
                }
            }
        }
    )
}
