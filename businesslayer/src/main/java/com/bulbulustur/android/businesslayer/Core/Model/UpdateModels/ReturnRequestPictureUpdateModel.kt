package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class ReturnRequestPictureUpdateModel(
    val ReturnRequestPictureId: Int = 0,
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
