package com.bulbulustur.android.businesslayer.Core.DTO

import com.bulbulustur.android.businesslayer.Core.Model.FilePropertyModel
data class ReviewPictureDTO(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,

    val ConfirmationStatusId: Int = 0,
    val ReviewId: Int = 0,

    val PictureName: String = "",
    val ContentType: String = "",
    val Length: Long = 0L,
    val DirectoryName: String = "",
    val Description: String = "",

    val FileProperty: List<FilePropertyModel> =
        emptyList()
)