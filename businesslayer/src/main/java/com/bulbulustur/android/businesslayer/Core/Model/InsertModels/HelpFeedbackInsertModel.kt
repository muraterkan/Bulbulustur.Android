package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class HelpFeedbackInsertModel(
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val HelpId: Int = 0,
    val Vote: Boolean = false
)
