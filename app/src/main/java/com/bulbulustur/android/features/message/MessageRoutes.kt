package com.bulbulustur.android.features.message

object MessageRoutes {

    const val Inbox = "message/inbox"

    const val Detail = "message/detail/{conversationId}"

    fun detail(conversationId: Long): String {
        return "message/detail/$conversationId"
    }
}