package com.bulbulustur.android.businesslayer.Core.DTO

data class ProductCategoryContentDTO(
    val ProductCategoryContentId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductCategoryContentGroupId: Int = 0,
    val ProductId: Int = 0,
    val ProductName: String = "",
    val MinimumOrderQuantity: Int = 0,
    val DefaultPicture: String = "",
    val VariantId: Int = 0,
    val Price: Double = 0.0,
    val StoreId: Int = 0,
    val ProductVariantPriceId: Int = 0,
    val ProductCategoryId: Int = 0,
    val Groups: List<ProductCategoryContentGroupDTO> = emptyList()
)
