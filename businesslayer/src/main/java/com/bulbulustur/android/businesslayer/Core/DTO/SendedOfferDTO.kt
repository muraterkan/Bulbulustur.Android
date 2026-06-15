package com.bulbulustur.android.businesslayer.Core.DTO

data class SendedOfferDTO(
    val SendedOfferId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val BuyerRequestId: Int = 0,
    val BuyerRequestKey: String = "",
    val AssignedToSellerId: Int = 0,
    val OfferDetail: String = ""
)
