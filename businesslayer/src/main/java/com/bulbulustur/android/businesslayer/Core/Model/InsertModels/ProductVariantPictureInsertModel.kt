package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class ProductVariantPictureInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductId: Int = 0,
    val ProductSecureKey: String = "",
    val VariantId: Int = 0,
    val VariantSecureKey: String = "",
    val ColorId: Int = 0,
    val PictureName: String = "",
    val ContentType: String = "",
    val Length: Long = 0L,
    val IsDefault: Int = 0,
    val Sorting: Int = 0,
    val DirectoryName: String = "",
    val Description: String = "",
    val ConfirmationStatusId: Int = 0
)
