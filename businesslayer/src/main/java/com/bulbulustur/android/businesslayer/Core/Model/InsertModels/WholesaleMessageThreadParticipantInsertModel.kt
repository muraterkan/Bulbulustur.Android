package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class WholesaleMessageThreadParticipantInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val WholesaleMessageThreadId: Int = 0,
    val MemberId: Int = 0
)
