package com.bulbulustur.android.businesslayer.Core.DTO

data class ProductVariantDTO(
    val VariantId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val VariantSecureKey: String = "",
    val ProductId: Int = 0,
    val ProductSecureKey: String = "",
    val ColorId: Int = 0,
    val SizeId: Int = 0,
    val Barcode: String = "",
    val CargoDesiId: Int = 0
)
