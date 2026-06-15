package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class WholesaleProductPriceInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val WholesaleProductId: Int = 0,
    val MinQuantity: Int = 0,
    val MaxQuantity: Int = 0,
    val UnitId: Int = 0,
    val Price: Double = 0.0,
    val CurrencyId: Int = 0,
    val SecureKey: String = ""
)
