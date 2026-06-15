package com.bulbulustur.android.businesslayer.Core.DTO

data class HelpFeedbackDTO(
    val FeedbackId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val HelpId: Int = 0,
    val Vote: Boolean = false
)
