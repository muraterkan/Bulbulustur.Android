package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class ReturnRequestPictureInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ConfirmationStatusId: Int = 0,
    val ReturnRequestId: Int = 0,
    val PictureName: String = "",
    val ContentType: String = "",
    val Length: Long = 0L,
    val DirectoryName: String = "",
    val Description: String = ""
)
