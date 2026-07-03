package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class WholesaleFavoriteUpdateModel(
    val WholesaleFavoriteId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val MemberId: Int = 0,
    val WholesaleProductId: Int = 0,
    val Note: String = ""
)