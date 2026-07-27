package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class TravelPhotoUpdateModel(
    val travelPhotoId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val travelId: Int,
    val photoUrl: String,
    val displayOrder: Int,
    val isCover: Boolean
)