package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class PropertyPhotoUpdateModel(
    val propertyPhotoId: Int,
    val propertyId: Int,
    val propertyRoomId: Int?,
    val photoUrl: String,
    val thumbnailUrl: String?,
    val fileName: String?,
    val displayOrder: Int,
    val isCover: Boolean,
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int
)