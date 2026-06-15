package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class WholesaleProductPictureUpdateModel(
    val WholesaleProductPictureId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val WholesaleProductId: Int = 0,
    val ColorId: Int = 0,
    val Picture: String = "",
    val ContentType: String = "",
    val Length: Long = 0L,
    val IsDefault: Int = 0,
    val Sorting: Int = 0,
    val DirectoryName: String = "",
    val Description: String = "",
    val ConfirmationStatusId: Int = 0,
    val SecureKey: String = ""
)
