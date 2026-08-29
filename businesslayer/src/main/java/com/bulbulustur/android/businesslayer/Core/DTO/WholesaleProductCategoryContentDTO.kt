package com.bulbulustur.android.businesslayer.Core.DTO

data class WholesaleProductCategoryContentDTO(
    val WholesaleProductCategoryContentId: Int = 0,
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductCategoryContentGroupId: Int = 0,
    val ProductId: Int = 0,
    val ProductName: String = "",
    val MinimumOrderQuantity: Int = 0,
    val WholesaleProductId: Int = 0,
    val DefaultPicture: String = "",
    val ProductCategoryId: Int = 0,
    val Groups: List<WholesaleProductCategoryContentGroupDTO> = emptyList()
)
