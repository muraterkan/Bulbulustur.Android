package com.bulbulustur.android.businesslayer.Core.DTO

data class ProductDTO(
    val ProductId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductName: String = "",
    val Description: String = "",
    val ProductCategoryId: Int = 0,
    val BrandId: Int = 0,
    val VatRateId: Int = 0,
    val ViewCount: Int = 0,
    val SeoTitle: String = "",
    val SeoDescription: String = "",
    val Rating: Double = 0.0,
    val ReviewNumber: Int? = null,
    val TotalSales: Int = 0,
    val ValidProductVariantId: Int = 0,
    val Uuid: String = "",
    val ProductSecureKey: String = "",
    val UnitId: Int = 0,
    val Buybox: Boolean = false
)
