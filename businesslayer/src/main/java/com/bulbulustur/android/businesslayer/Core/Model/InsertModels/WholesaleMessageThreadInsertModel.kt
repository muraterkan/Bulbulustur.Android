package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class WholesaleMessageThreadInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val SenderId: Int = 0,
    val RecipientId: Int = 0
)
