package com.bulbulustur.android.businesslayer.Core.DTO

import com.bulbulustur.android.businesslayer.Core.Model.FilePropertyModel
data class ReviewPictureDTO(
    val ReviewPictureId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String? = null,
    val StatusId: Int = 0,
    val ConfirmationStatusId: Int = 0,
    val ReviewId: Int = 0,
    val PictureName: String? = null,
    val ContentType: String? = null,
    val Length: Long = 0L,
    val DirectoryName: String? = null,
    val Description: String? = null,
    val FileProperty: List<FilePropertyModel> =
        emptyList()
)