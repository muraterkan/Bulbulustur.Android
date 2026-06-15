package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class SendedOfferInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val BuyerRequestId: Int = 0,
    val BuyerRequestKey: String = "",
    val AssignedToSellerId: Int = 0,
    val OfferDetail: String = ""
)
