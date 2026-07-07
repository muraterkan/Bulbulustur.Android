package com.bulbulustur.android.businesslayer.Core.DTO

data class WholesaleHomepageFeaturedProductDTO(
    val WholesaleHomepageFeaturedProductId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductId: Int = 0,
    val CompanyId: Int = 0,
    val SortOrder: Int = 0,
    val StartingDate: String? = null,
    val EndingDate: String? = null,
    val IsManual: Boolean = false,
    val ProductName: String = "",
    val MinimumOrderQuantity: Int = 0,
    val MinimumOrderUnitId: Int = 0,
    val WholesaleProductId: Int = 0,
    val DefaultPicture: String = ""
)