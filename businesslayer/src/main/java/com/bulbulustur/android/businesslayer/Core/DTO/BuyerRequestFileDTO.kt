package com.bulbulustur.android.businesslayer.Core.DTO

data class BuyerRequestFileDTO(
    val BuyerRequestFileId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val BuyerRequestId: Int = 0,
    val BuyerRequestKey: String = "",
    val PictureName: String = "",
    val ContentType: String = "",
    val Length: Long = 0L,
    val IsDefault: Int = 0,
    val DirectoryName: String = "",
    val Description: String = "",
    val ConfirmationTypeId: Int = 0
)
