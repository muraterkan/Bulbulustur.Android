package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class CompanyPictureInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CompanyId: Int = 0,
    val PictureName: String = "",
    val ContentType: String = "",
    val Length: Long = 0,
    val IsDefault: Int = 0,
    val Sorting: Int = 0,
    val DirectoryName: String = "",
    val Description: String = "",
    val SecureKey: String = ""
)
