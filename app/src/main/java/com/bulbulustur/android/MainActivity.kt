package com.bulbulustur.android

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bulbulustur.android.Application.BulbulusturApp

class MainActivity : ComponentActivity() {

    private var appLinkUrl by
    mutableStateOf<String?>(
        null
    )

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )

        appLinkUrl =
            intent?.dataString

        setContent {
            BulbulusturApp(
                appLinkUrl =
                    appLinkUrl,
                onAppLinkConsumed = {
                    appLinkUrl =
                        null
                }
            )
        }
    }

    override fun onNewIntent(
        intent: Intent
    ) {
        super.onNewIntent(
            intent
        )

        setIntent(
            intent
        )

        appLinkUrl =
            intent.dataString
    }
}