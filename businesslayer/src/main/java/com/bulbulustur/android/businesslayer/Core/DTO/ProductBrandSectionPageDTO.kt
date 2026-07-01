package com.bulbulustur.android.businesslayer.Core.DTO

data class ProductBrandSectionPageDTO(
    val ProductBrandSectionPageId: Int = 0,
    val ProductBrandSectionId: Int = 0,

    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,

    val Title: String = "",
    val SubTitle: String = "",
    val Content: String = "",

    val Picture: String = "",
    val MobilePicture: String = "",
    val Link: String = "",

    val ProductId: Int = 0,
    val VariantId: Int = 0,
    val StoreId: Int = 0,
    val ProductCategoryId: Int = 0,

    val OrderNo: Int = 0
)