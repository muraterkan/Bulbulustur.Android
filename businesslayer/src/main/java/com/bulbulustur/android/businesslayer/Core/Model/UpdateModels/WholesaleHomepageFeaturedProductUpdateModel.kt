package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class WholesaleHomepageFeaturedProductUpdateModel(
    val WholesaleHomepageFeaturedProductId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductId: Int = 0,
    val CompanyId: Int = 0,
    val SortOrder: Int = 0,
    val StartingDate: String? = null,
    val EndingDate: String? = null,
    val IsManual: Boolean = false
)
