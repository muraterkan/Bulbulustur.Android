package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class AssignedToSellerUpdateModel(
    val AssignedToSellerId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val BuyerRequestId: Int = 0,
    val BuyerRequestKey: String = "",
    val AssignedMemberId: Int = 0,
    val OfferStatus: Int = 0
)
