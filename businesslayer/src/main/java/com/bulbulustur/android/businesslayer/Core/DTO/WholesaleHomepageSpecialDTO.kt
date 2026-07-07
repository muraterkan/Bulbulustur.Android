package com.bulbulustur.android.businesslayer.Core.DTO

data class WholesaleHomepageSpecialDTO(
    val WholesaleHomepageSpecialId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductSpecialGroupId: Int = 0,
    val ProductId: Int = 0,
    val ProductName: String = "",
    val WholesaleProductId: Int = 0,
    val MinimumOrderQuantity: Int = 0,
    val DefaultPicture: String = ""
)