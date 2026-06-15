package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class WholesaleMessageThreadUpdateModel(
    val WholesaleMessageThreadId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val SenderId: Int = 0,
    val RecipientId: Int = 0
)
