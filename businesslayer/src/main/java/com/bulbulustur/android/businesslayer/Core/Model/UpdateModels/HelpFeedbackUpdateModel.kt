package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class HelpFeedbackUpdateModel(
    val FeedbackId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val HelpId: Int = 0,
    val Vote: Boolean = false
)
