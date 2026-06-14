package com.bulbulustur.android.Features.message

object MessageRoutes {
    const val Inbox = "message/inbox"

    const val Detail = "message/detail/{messageId}"
    const val ArgMessageId = "messageId"

    fun detail(messageId: Int): String {
        return "message/detail/$messageId"
    }
}